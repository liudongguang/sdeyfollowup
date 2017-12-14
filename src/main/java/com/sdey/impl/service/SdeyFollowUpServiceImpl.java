package com.sdey.impl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldg.api.util.*;
import com.ldg.api.vo.PageParam;
import com.sdey.api.constant.SysConstant;
import com.sdey.api.po.*;
import com.sdey.api.service.SdeyFollowUpService;
import com.sdey.api.vo.*;
import com.sdey.api.vo.ext.*;
import com.sdey.api.vo.hisoutpatient.InterfaceInfo;
import com.sdey.api.vo.hisoutpatient.PatinetInfo;
import com.sdey.api.vo.tongji.DisplayTJINFO;
import com.sdey.api.vo.tongji.GetTongjiDataParam;
import com.sdey.api.vo.tongji.TongjiData;
import com.sdey.impl.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liudo on 2017/3/15 0015.
 */
@Service
public class SdeyFollowUpServiceImpl implements SdeyFollowUpService {
    public final static Logger logger = LoggerFactory.getLogger(SdeyFollowUpServiceImpl.class);

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ImportlogMapper importlogMapper;
    @Autowired
    private PatientsMapper patientsMapper;
    @Autowired
    private FollowuplogMapper followuplogMapper;
    @Autowired
    private ManagerworkMapper managerworkMapper;
    @Autowired
    private FollowuplogmessageMapper followuplogmessageMapper;
    @Autowired
    private PhoneblacklistMapper phoneblacklistMapper;

    @Override
    public PageInfo<Manager> getPageInfo(PageParam pageParam) {
        PageInfo<Manager> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> managerMapper.selectAll());
        return pageInfo;
    }

    @Override
    public PageInfo<DisPatients> getOutHosMembers(PageParam pageParam, DisSearch search) {
        PageInfo<DisPatients> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> patientsMapper.selectDisPatients(search));
        return pageInfo;
    }

    @Override
    public PageInfo<DisPatients> getOutHosMembers2(PageParam pageParam, DisSearch search) {
        PageInfo<DisPatients> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> patientsMapper.selectDisPatients2(search));
        if (pageInfo.getList() == null || pageInfo.getList().size() == 0) {
            return pageInfo;
        }
        List<Integer> ids = new ArrayList<>();
        pageInfo.getList().forEach(item -> {
            if (null != item) {
                //获取followupstate，manyistate  获取随访记录第一条
                PageInfo<DisPatients> sdeyfollowupPageInfo = PageHelper.startPage(1, 1, false).doSelectPageInfo(() -> followuplogMapper.findStateAndManyi(item.getUid()));
                List<DisPatients> sdeyfollowupList = sdeyfollowupPageInfo.getList();
                if (sdeyfollowupList != null && sdeyfollowupList.size() == 1) {
                    DisPatients sdeyfollowup = sdeyfollowupList.get(0);
                    if (null != sdeyfollowup) {
                        item.setFollowupstate(sdeyfollowup.getFollowupstate());
                        item.setManyistate(sdeyfollowup.getManyistate());
                    }
                }
                ///////短信回复情况
                PageInfo<DisPatients> messagePageInfo = PageHelper.startPage(1, 1, false).doSelectPageInfo(() -> followuplogmessageMapper.findDxcontentAndDxState(item.getUid()));
                List<DisPatients> messageList = messagePageInfo.getList();
                if (messageList != null && messageList.size() == 1) {
                    DisPatients message = messageList.get(0);
                    if (null != message) {
                        item.setDxcontent(message.getDxcontent());
                    }
                }
            }
        });
        return pageInfo;
    }

    @Override
    public Patients getPationtById(Integer uid) {
        return patientsMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void addInfo() {
        for (int i = 0; i < 100; i++) {
            Manager m = new Manager();
            m.setName("aa" + i);
            m.setCreatetime(new Date());
            m.setPass("1111");
            m.setUsername("bb" + i);
            managerMapper.insert(m);
        }
    }

    @Override
    public InterfaceInfo getOneMonthHisChuYuanPatient(String date) {
        String getUrl = PropertiesUtil.getHospitalInterfacePropertiesVal("getOneMonthHisChuYuanPatient");
        HttpClientUtil htc = HttpClientUtil.getInstance();
        String rsStr = htc.sendHttpGet(MessageFormat.format(getUrl, date));
        if (rsStr == null) {
            return null;
        }
        InterfaceInfo info = JsonUtil.getObjectByJSON(rsStr, InterfaceInfo.class);
        return info;
    }

    @Override
    public String handlerOneMonthHisChuYuanPatient(String date) {
        Integer logid = importlogMapper.selectByDate(date);
        //1.没有记录才能添加
        if (logid == null) {
            InterfaceInfo oneMonthHisChuYuanPatient = this.getOneMonthHisChuYuanPatient(date);
            if (oneMonthHisChuYuanPatient == null) {
                return "与医院接口通信出错！";
            }
            Date createTime = new Date();
            if (oneMonthHisChuYuanPatient.getCode() == 0) {
                oneMonthHisChuYuanPatient.getData().forEach((PatinetInfo item) -> {
                    Patients p = null;
                    try {
                        p = item.reverse();
                    } catch (ParseException e) {
                        logger.error(e.toString());
                        e.printStackTrace();
                    }
                    p.setCreatetime(createTime);
                    p.setImportdatestr(date);
                    patientsMapper.insert(p);
                });
                ///插入到记录表
                Importlog log = new Importlog();
                log.setCreatetime(createTime);
                log.setImportdatestr(date);
                importlogMapper.insert(log);
            } else {
                return oneMonthHisChuYuanPatient.getHeader().getErrreason();
            }
        } else {
            return "记录已生成！";
        }
        return null;
    }

    @Override
    public Manager login(Manager loginUser) {
        return managerMapper.login(loginUser);
    }

    @Override
    public int saveSuiFangLog(Followuplog savelog, Integer workid) {
        savelog.setUid(null);
        savelog.setCreatetime(new Date());
        //1.是否已有记录，有则不是第一条随访记录，不用设置第一条标识
//        Integer logID = followuplogMapper.selectByPatientIDAndFirstState(savelog);
//        if (logID == null) {
//            savelog.setIsfirstlog(1);
//        }
        //修改工作完成状态
        finishiwork(workid);
        return followuplogMapper.insertSelective(savelog);
    }

    @Override
    public PageInfo<Followuplog> getpationtSFLogs(Integer uid, PageParam pageParam) {
        PageInfo<Followuplog> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> followuplogMapper.getpationtSFLogs(uid));
        return pageInfo;
    }

    @Override
    public PageInfo<Manager> getManagerList(PageParam pageParam) {
        PageInfo<Manager> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> managerMapper.getManagerList());
        return pageInfo;
    }

    @Override
    public int saveManager(Manager manager) {
        manager.setCreatetime(new Date());
        return managerMapper.insertSelective(manager);
    }

    @Override
    public int delManager(Integer uid) {
        return managerMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public Integer checkManagerUserName(String username) {
        return managerMapper.checkManagerUserName(username);
    }

    @Override
    public List<GroupImportlogForFenPei> getKeFenPeiList() {
        return importlogMapper.getKeFenPeiList();
    }

    @Override
    public void fenpeiWork(fenpeiWorkParam param) {
        //1.获取这天的记录
        String fenpeiDate = param.getFenpeiDate();
        List<Integer> listByDate = patientsMapper.selectUidsByDate(fenpeiDate);
        int userSize = param.getUserid().size();
        List<List<Integer>> pingfenList = LdgListUtil.splitListForWork(listByDate, userSize);
        int i = 0;
        //2.增加到工作记录表
        Date nowDate = new Date();
        for (Integer userid : param.getUserid()) {
            List<Integer> patientids = pingfenList.get(i);
            for (Integer patientid : patientids) {
                Managerwork mk = new Managerwork();
                mk.setManagerid(userid);
                mk.setPatientsid(patientid);
                mk.setCreatetime(nowDate);
                managerworkMapper.insertSelective(mk);
            }
            i++;
        }
        //3.更新分配状态 Importlog
        int updateState = importlogMapper.updateFenpeiWorkState(fenpeiDate);
    }

    private boolean deathSate(String zgqk) {
        return SysConstant.DEATH_STR.equals(zgqk) || (zgqk != null && zgqk.indexOf(SysConstant.DEATH_STR) != -1);
    }

    @Override
    public int fenpeiWorkPlus(fenpeiWorkParam param) {
        //1.获取这天的记录
        String fenpeiDate = param.getFenpeiDate();
        List<FenpeiWorkPlus> listByDate = patientsMapper.selectUidsByDatePlus(fenpeiDate);
        Map<String, List<FenpeiWorkPlus>> groupRs = listByDate.stream().filter(item -> {
            //联系电话或家庭电话是正确号码且 不是死亡   不过滤,留下
            String zgqk = item.getZgqk();
            if (deathSate(zgqk) || !(LdgStringUtil.isPhoneLegal(item.getLxdh()) || LdgStringUtil.isPhoneLegal(item.getJtdh()))) {
                return false;  //死亡去除或者电话都不合理的时候去除
            }
            //
            return true;
        }).collect(Collectors.groupingBy(FenpeiWorkPlus::getKsName));
        ////////不能随访的记录
        List<FenpeiWorkPlus> notSuifangList = listByDate.stream().filter(item -> {
            String zgqk = item.getZgqk();
            if (deathSate(zgqk) || !(LdgStringUtil.isPhoneLegal(item.getLxdh()) || LdgStringUtil.isPhoneLegal(item.getJtdh()))) {
                return true;  //死亡留下 或者 电话号码都不合理的时候留下
            }
            return false;
        }).collect(Collectors.toList());
        //////////////////////////////////////////////////
        Date nowDate = new Date();
        notSuifangList.forEach(item -> {
            Managerwork mk = new Managerwork();
            //mk.setManagerid(userid);
            mk.setPatientsid(item.getPationtID());
            mk.setCreatetime(nowDate);
            mk.setFenpeidatestr(fenpeiDate);
            if (deathSate(item.getZgqk())) {
                mk.setExcudenum(SysConstant.SF_EXCUTE_DIE);
            } else {
                mk.setExcudenum(SysConstant.SF_EXCUTE_NOPHONE);
            }
            managerworkMapper.insertSelective(mk);
        });
        ////////////////////针对人数进行分组/////////////////////////////
        int userSize = param.getUserid().size();
        boolean duogeManagerState = false;//默认分配一个人
        if (userSize > 1) {
            duogeManagerState = true;
        }
        //循环各个科室
        List<FenpeiWorkPlus> duoyuList = new ArrayList<>();//每个可以多余的一个两个的会
        groupRs.forEach((key, val) -> {
            ///对科室病人均分
            List<List<FenpeiWorkPlus>> pingfenList = LdgListUtil.splitList(val, userSize); //最后会有余数的list
            //2.增加到工作记录表
            int i = 0;
            for (Integer userid : param.getUserid()) {
                List<FenpeiWorkPlus> patientids = pingfenList.get(i);
                for (FenpeiWorkPlus patient : patientids) {
                    Managerwork mk = new Managerwork();
                    mk.setManagerid(userid);
                    mk.setPatientsid(patient.getPationtID());
                    mk.setCreatetime(nowDate);
                    mk.setFenpeidatestr(fenpeiDate);
                    managerworkMapper.insertSelective(mk);
                }
                i++;
            }
            if (pingfenList.size() != userSize) {
                duoyuList.addAll(pingfenList.get(i));
            }
        });
        ////对每个科室有剩余的再次分配
        if (duogeManagerState && duoyuList.size() != 0) {
            List<List<FenpeiWorkPlus>> pingfenList = LdgListUtil.splitListForWork(duoyuList, userSize);
            int j = 0;
            for (Integer userid : param.getUserid()) {
                List<FenpeiWorkPlus> patientids = pingfenList.get(j);
                patientids.forEach((item) -> {
                    Managerwork lastmk = new Managerwork();
                    lastmk.setPatientsid(item.getPationtID());
                    lastmk.setManagerid(userid);
                    lastmk.setCreatetime(nowDate);
                    lastmk.setFenpeidatestr(fenpeiDate);
                    managerworkMapper.insertSelective(lastmk);
                });
                j++;
            }
        }
        ////
        //3.更新分配状态 Importlog
        return importlogMapper.updateFenpeiWorkState(fenpeiDate);
    }

    @Override
    public List<ManagerWork> getManagerWork(String dateStr) {
        return managerworkMapper.getManagerWork(dateStr);
    }

    @Override
    public int finishiwork(Integer workid) {
        Managerwork managerwork = managerworkMapper.selectNotfinishiwork(workid);
        if (managerwork != null) {
            managerwork = new Managerwork();
            managerwork.setUid(workid);
            managerwork.setWorkstate(1);
            managerwork.setOvertime(new Date());
            return managerworkMapper.finishiwork(managerwork);
        }
        return 0;
    }

    @Override
    public List<String> getListKsName() {
        return patientsMapper.getListKsName();
    }

    @Override
    public String updatePass(UpdatePassParam param) {
        //是否有这个人
        Integer uid = managerMapper.selectManagerIDByUidAndPass(param);
        if (uid == null) {
            return "用户名或密码错误！";
        } else {
            int i = managerMapper.updatePasswordByUid(param);
            if (i == 0) {
                return "修改失败！";
            }
        }
        return null;
    }

    @Override
    public PageInfo<Importlog> getImportlogPageInfo(PageParam pageParam, String thisYear) {
        PageInfo<Importlog> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> importlogMapper.selectAllByYear(thisYear));
        return pageInfo;
    }

    @Override
    public PageInfo<Importlog> getImportlogPageInfo(PageParam pageParam) {
        PageInfo<Importlog> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> importlogMapper.selectAllForOrderID());
        return pageInfo;
    }

    @Override
    public List<Importlog> getyiFenpeiList() {
        return importlogMapper.getyiFenpeiList();
    }

    @Override
    public List<GetFenPeiDetailForOfficeRs> getFenPeiDetailForOffice(GetFenPeiDetailForOfficeParam param) {
        return managerworkMapper.getFenPeiDetailForOffice(param);
    }

    @Override
    public List<TongjiData> getTongjiData(GetTongjiDataParam param) {
        return patientsMapper.getTongjiData(param);
    }

    @Override
    public HandlertjDataRs handlertjData(GetTongjiDataParam param) {

        List<TongjiData> tjlist = getTongjiData(param); //获取统计数据

        List<DisplayTJINFO> rsList = new ArrayList<>();
        Map<String, List<TongjiData>> ksMap = tjlist.stream().collect(Collectors.groupingBy(TongjiData::getCyks));
        ////总计
        final int[] zwdjCount = {0};//未登记总数
        final int[] zhfCount = {0};//回访人数总数
        final int[] zhfwlxsCount = {0};//未联系上总数
        final int[] zmanyiCount = {0};//满意总数
        final int[] zCyCount = {0};//出院总数
        ////
        final int[] sendMsgCount = {0};//发送短信数
        final int[] receiveCount = {0};//接受短信数
        final int[] goodReceiveCount = {0};//短信好评数
        ksMap.forEach((key, val) -> {
            DisplayTJINFO tj = new DisplayTJINFO();
            tj.setKsName(key);
            tj.setCyCount(val.size());
            final int[] wdjCount = {0};//未登记
            final int[] hfCount = {0};//回访人数
            final int[] hfwlxsCount = {0};//未联系上
            final int[] manyiCount = {0};//满意个数
            val.forEach((item) -> {
                Integer finishstate = item.getFinishstate();//完成状态
                Integer excudenum = item.getExcudenum();  //1.未登记   2.死亡
                Integer followupstate = item.getFollowupstate();//随访状态  1.未随访  2.以随访  3.联系不上
                Integer manyistate = item.getManyistate();//   满意状态 满意情况   1 满意  2不满意
                Integer dxpationtid = item.getDxpationtid();//短信病人id   不为null说明发送过短信
                String dxcontent = item.getDxcontent();//短信内容
                if (excudenum != null && excudenum == 1) {
                    wdjCount[0]++;
                }
                if (finishstate != null && finishstate == 1) {
                    hfCount[0]++;
                }
                if (followupstate != null && followupstate == 3) {
                    hfwlxsCount[0]++;
                }
                if (manyistate != null && manyistate == 1) {
                    manyiCount[0]++;
                }
//                else if (dxpationtid != null && (dxcontent != null && !(dxcontent.contains("2") || dxcontent.equals("不满意")))) {
//                    manyiCount[0]++;
//                    //短信好评数
//                    goodReceiveCount[0]++;
//                }
                ///短信发送数
                if (dxpationtid != null) {
                    sendMsgCount[0]++;
                    if (dxcontent != null &&dxcontent.contains("1")||dxcontent==null) {
                        manyiCount[0]++;
                        //短信好评数
                        goodReceiveCount[0]++;
                    }
                }
                //接受短信数
                if (dxcontent != null) {
                    receiveCount[0]++;
                }
            });
            tj.setWdjCount(wdjCount[0]);
            tj.setHfCount(hfCount[0]);
            tj.setHfwlxsCount(hfwlxsCount[0]);
            tj.setManyiCount(manyiCount[0]);
            if (tj.getCyCount() != 0) {
                String djlv = LdgNumberUtil.getBaifenbi((tj.getCyCount() - tj.getWdjCount()) * 1.0 / tj.getCyCount());//登记率    （出院人数-未登记）/出院人数
                String hflv = LdgNumberUtil.getBaifenbi(tj.getHfCount() * 1.0 / tj.getCyCount());//回访人数/出院人数
                String mylv = LdgNumberUtil.getBaifenbi(tj.getManyiCount() * 1.0 / tj.getCyCount());//满意个数/出院人数
                tj.setDjlv(djlv);
                tj.setHflv(hflv);
                tj.setMylv(mylv);
            }
            ////总计叠加
            zwdjCount[0] += tj.getWdjCount();
            zhfCount[0] += tj.getHfCount();
            zhfwlxsCount[0] += tj.getHfwlxsCount();
            zmanyiCount[0] += tj.getManyiCount();
            zCyCount[0] += tj.getCyCount();
            ////
            rsList.add(tj);
        });
        //总计
        DisplayTJINFO ztj = new DisplayTJINFO();
        ztj.setKsName("总计：");
        ztj.setCyCount(zCyCount[0]);
        ztj.setWdjCount(zwdjCount[0]);
        ztj.setHfCount(zhfCount[0]);
        ztj.setHfwlxsCount(zhfwlxsCount[0]);
        ztj.setManyiCount(zmanyiCount[0]);
        if (ztj.getCyCount() != 0) {
            String mylv = LdgNumberUtil.getBaifenbi(ztj.getManyiCount() * 1.0 / ztj.getCyCount());//满意个数/出院人数
            String djlv = LdgNumberUtil.getBaifenbi((ztj.getCyCount() - ztj.getWdjCount()) * 1.0 / ztj.getCyCount());//登记率    （出院人数-未登记）/出院人数
            String hflv = LdgNumberUtil.getBaifenbi(ztj.getHfCount() * 1.0 / ztj.getCyCount());//回访人数/出院人数
            ztj.setDjlv(djlv);
            ztj.setHflv(hflv);
            ztj.setMylv(mylv);
        }
        rsList.add(ztj);
        /////////////////////////////////////////////////////短信统计
        StatisticsMessage smsg = new StatisticsMessage();
        smsg.setSendCount(sendMsgCount[0]);
        smsg.setReceiveCount(receiveCount[0]);
        smsg.setGoodReceiveCount(goodReceiveCount[0]);
        //短信好评率
        if (smsg.getSendCount() != 0) {
            smsg.setHaopinglvStr(LdgNumberUtil.getBaifenbi(smsg.getGoodReceiveCount() * 1.0 / smsg.getSendCount()));
        }
        HandlertjDataRs hrs = new HandlertjDataRs();
        hrs.setRsList(rsList);
        hrs.setStatistMsg(smsg);
        return hrs;
    }



    @Override
    public PageInfo<Phoneblacklist> getPhoneBlacklist(PageParam pageParam) {
        PageInfo<Phoneblacklist> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true).doSelectPageInfo(() -> phoneblacklistMapper.getPhoneBlacklist());
        return pageInfo;
    }

    @Override
    public int saveBlackPhone(Phoneblacklist param) {
        param.setCreatetime(new Date());
        return phoneblacklistMapper.insertSelective(param);
    }

    @Override
    public Integer checkBlackPhone(String phone) {
        return phoneblacklistMapper.checkBlackPhone(phone);
    }

    @Override
    public int delphoneblacklist(Phoneblacklist param) {
        return phoneblacklistMapper.deleteByPrimaryKey(param.getUid());
    }
}
