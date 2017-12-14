<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>病人姓名:${param.brxm} 住院号:${param.zyh} 出院科室:${param.cyks}</div>
<div class="tab">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist" id="templatemo-tabs">
        <li class="active"><a href="#yishengzhenduan" role="tab" data-toggle="tab">医生诊断</a></li>
        <li class=""><a href="#shoushujilu" role="tab" data-toggle="tab">手术记录</a></li>
        <li class=""><a href="#changqiyizhu" role="tab" data-toggle="tab">长期医嘱</a></li>
        <li class=""><a href="#linshiyizhu" role="tab" data-toggle="tab">临时医嘱</a></li>
        <li class=""><a href="#chuyuandaiyao" role="tab" data-toggle="tab">出院带药</a></li>
        <li class=""><a href="#binganshouye" role="tab" data-toggle="tab">病案首页</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content" style="margin-top:5px;">
        <div class="tab-pane fade active in" id="yishengzhenduan">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>疾病名称</th>
                    <th>诊断医生</th>
                    <th>诊断时间</th>
                    <th>诊断类别</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${zyjl.yszdList}" var="obj">
                    <tr>
                        <th>疾病名称</th>
                        <th>诊断医生</th>
                        <th>诊断时间</th>
                        <th>诊断类别</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="shoushujilu">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>手术名称</th>
                    <th>手术医生</th>
                    <th>手术等级</th>
                    <th>手术开始时间</th>
                    <th>手术结束时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${zyjl.ssjlList}" var="obj">
                    <tr>
                        <td>${obj.ssmc}</td>
                        <td>${obj.ssys}</td>
                        <td>${obj.ssdj}</td>
                        <td>${obj.kssj}</td>
                        <td>${obj.jssj}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="changqiyizhu">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>医嘱名称</th>
                    <th>一次剂量</th>
                    <th>使用频次</th>
                    <th>药品用法</th>
                    <th>发药时间</th>
                    <th>开嘱医生</th>
                    <th>开嘱时间</th>
                    <th>停嘱医生</th>
                    <th>停嘱时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${zyjl.cqyzList}" var="obj">
                    <tr>
                        <td>${obj.yzmc}</td>
                        <td>${obj.ycjl}</td>
                        <td>${obj.sypc}</td>
                        <td>${obj.ypyf}</td>
                        <td></td>
                        <td>${obj.ysgh_ks}</td>
                        <td>${obj.kssj}</td>
                        <td>${obj.ysgh_tz}</td>
                        <td>${obj.tzsj}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="linshiyizhu">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>医嘱名称</th>
                    <th>一次剂量</th>
                    <th>使用频次</th>
                    <th>药品用法</th>
                    <th>发药时间</th>
                    <th>开嘱医生</th>
                    <th>开嘱时间</th>
                    <th>停嘱医生</th>
                    <th>停嘱时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${zyjl.lsyzList}" var="obj">
                    <tr>
                        <td>${obj.yzmc}</td>
                        <td>${obj.ycjl}</td>
                        <td>${obj.sypc}</td>
                        <td>${obj.ypyf}</td>
                        <td></td>
                        <td>${obj.ysgh_ks}</td>
                        <td>${obj.kssj}</td>
                        <td>${obj.ysgh_tz}</td>
                        <td>${obj.tzsj}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="chuyuandaiyao">
            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <th>医嘱名称</th>
                    <th>一次剂量</th>
                    <th>使用频次</th>
                    <th>药品用法</th>
                    <th>发药方式</th>
                    <th>开嘱医生</th>
                    <th>开嘱时间</th>
                    <th>停嘱医生</th>
                    <th>停嘱时间</th>
                    <th>是否带药</th>
                </tr>
                </thead>

                <tbody>

                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="binganshouye">
            <div class="bingan-top">
                <h3>医疗机构<strong>山东大学第二医院</strong></h3>
                <p>（组织机构代码：49557309-6）</p>
                <h3 style="margin-top:0">住 院 病 案 首 页</h3>
            </div>
            <div class="form-inline" style="margin:15px 0">
                <div class="form-control top-fm">
                    <label>医疗付款方式：</label>
                    <span>${bingan.brxz}</span></div>
                <!--
                <div class="form-control top-fm">
                    <label>健康卡号：</label>
                    <span>${bingan.bahm}</span></div>
                    -->
                <div class="form-control top-fm">
                    <label>第<span>${bingan.zycs}</span>次住院</label>
                </div>
                <div class="form-control top-fm">
                    <label>病案号：</label>
                    <span>${bingan.bahm}</span></div>
            </div>
            <table class="table table-hover table-bordered">
                <tbody>
                <tr>
                    <td>姓名:</td>
                    <td>${bingan.xm}</td>
                    <td>性别：</td>
                    <td><label class="pot-name">${bingan.xb}</label>
                    </td>
                    <td>出生日期：</td>
                    <td>${bingan.csrq}</td>
                    <td>年龄：</td>
                    <td>${bingan.nl}</td>
                    <td>国籍：</td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">（年龄不足1周岁的）年龄：</td>
                    <td>${bingan.bzyzsnl}</td>
                    <td colspan="2">新生儿出生体重：</td>
                    <td>${bingan.xsecstz}</td>
                    <td colspan="2">新生儿入院体重：</td>
                    <td>${bingan.xserytz}</td>
                    <td></td>
                </tr>
                <tr>
                    <td>出生地：</td>
                    <td>${bingan.csd}</td>
                    <td> 籍贯：</td>
                    <td>${bingan.jg}</td>
                    <td>民族：</td>
                    <td>${bingan.mz}</td>
                    <td>身份证号：</td>
                    <td colspan="3">${bingan.sfzh}</td>
                </tr>
                <tr>
                    <td>职业：</td>
                    <td>${bingan.zy}</td>
                    <td>婚姻：</td>
                    <td>${bingan.hy}</td>
                    <td>现住址：</td>
                    <td>${bingan.xzz}</td>
                    <td>电话</td>
                    <td>${bingan.dh}</td>
                    <td>邮编</td>
                    <td>${bingan.yb1}</td>
                </tr>
                <tr>
                    <td>职业：</td>
                    <td></td>
                    <td>婚姻：</td>
                    <td></td>
                    <td>现住址：</td>
                    <td colspan="2"></td>
                    <td>电话</td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td>邮编:</td>
                    <td>${bingan.yb2}</td>
                    <td>户口地址：</td>
                    <td>${bingan.hkdz}</td>
                    <td>邮编：</td>
                    <td></td>
                    <td>工作单位及地址</td>
                    <td colspan="3">${bingan.gzdwdz}</td>
                </tr>
                <tr>
                    <td>邮编:</td>
                    <td colspan="9">${bingan.yb3}</td>
                </tr>
                <tr>
                    <td>联系人:</td>
                    <td>${bingan.lxrxm}</td>
                    <td>关系：</td>
                    <td>${bingan.gx}</td>
                    <td>住址：</td>
                    <td colspan="2">${bingan.lxdz}</td>
                    <td>电话：</td>
                    <td colspan="2">${bingan.lxdh2}</td>
                </tr>
                <tr>
                    <td>入院途径：</td>
                    <td colspan="4"><label class="pot-name">${bingan.rytj}</label>
                        <!--
                        1.急诊 2.门诊 3.其他医疗机构转入 9.其他
                        -->
                    </td>
                    <td>入院日期：</td>
                    <td colspan="4">${bingan.rysj}</td>
                </tr>
                <tr>
                    <td>入院科别：</td>
                    <td>${bingan.rykb}</td>
                    <td>病房：</td>
                    <td></td>
                    <td>转科科别：</td>
                    <td>${bingan.zkkb}</td>
                    <td>出院日期：</td>
                    <td colspan="3">${bingan.cysj}</td>
                </tr>
                <tr>
                    <td>出院科别：</td>
                    <td>${bingan.cykb}</td>
                    <td>实际住院：</td>
                    <td>${bingan.sjzyts}</td>
                    <td>门（急）诊诊断：</td>
                    <td colspan="2">${bingan.mzzd}</td>
                    <td>疾病编码：</td>
                    <td colspan="2"></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-hover table-bordered">
                <thead class="th-bg">
                <th>出院诊断</th>
                <th>疾病编码</th>
                <th>入院病情</th>
                <th>出院诊断</th>
                <th>疾病编码</th>
                <th>入院病情</th>
                </thead>
                <tbody>
                <tr>
                    <td>${bingan.qtzd1}</td>
                    <td></td>
                    <td>${bingan.rybq1}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>${bingan.qtzd2}</td>
                    <td></td>
                    <td>${bingan.rybq2}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>${bingan.qtzd3}</td>
                    <td></td>
                    <td>${bingan.rybq3}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>${bingan.qtzd4}</td>
                    <td></td>
                    <td>${bingan.rybq4}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>${bingan.qtzd5}</td>
                    <td></td>
                    <td>${bingan.rybq5}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-hover table-bordered">
                <tbody>
                <tr>
                    <td>入院病情：</td>
                    <td><label class="pot-name">${bingan.rybq1}</label>
                        <!--
                        1.有 2.临床未确定 3.情况不明 4.无
                        -->
                    </td>
                    <td colspan="6"></td>
                </tr>
                <tr>
                    <td>操作、中毒外部原因：</td>
                    <td>${bingan.sszdbz}</td>
                    <td>疾病编码：</td>
                    <td colspan="5">${bingan.ssbm}</td>
                </tr>
                <tr>
                    <td>病理诊断：</td>
                    <td>${bingan.blzd}</td>
                    <td>疾病编码：</td>
                    <td>${bingan.blbm}</td>
                    <td>病理号：</td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td>药物过敏：</td>
                    <td>
                    </td>
                    <td>过敏药物：</td>
                    <td>${bingan.gmyw}</td>
                    <td>死亡患者尸检：</td>
                    <td>${bingan.sjbz}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>血型：</td>
                    <td colspan="2"><label class="pot-name">${bingan.xx}</label>
                        <!--
                        1.A 2.B 3.AB 4.O 5.不详 6.未查
                        -->
                    </td>
                    <td>RH：</td>
                    <td colspan="4"><label class="pot-name"> ${bingan.rh} </label>
                        <!--
                        1.阴 2.阳 3.不详 4.未查
                        -->
                    </td>
                </tr>
                <tr>
                    <td>科主任：</td>
                    <td>${bingan.kzr}</td>
                    <td> 主（副主）任医师：</td>
                    <td>${bingan.zrys}</td>
                    <td> 主治医师：</td>
                    <td>${bingan.zzys}</td>
                    <td> 住院医师：</td>
                    <td>${bingan.zyys}</td>
                </tr>
                <tr>
                    <td>责任护士：</td>
                    <td></td>
                    <td> 进修医师：</td>
                    <td></td>
                    <td>实习医师：</td>
                    <td></td>
                    <td>编码员：</td>
                    <td></td>
                </tr>
                <tr>
                    <td>病案质量：</td>
                    <td>- 1.甲 2.乙 3.丙</td>
                    <td>质控医师：</td>
                    <td>-</td>
                    <td>质控护士：</td>
                    <td>-</td>
                    <td> 质控日期：</td>
                    <td>-年-月-日</td>
                </tr>
                </tbody>
            </table>
            <table class="table table-hover table-bordered">
                <thead class="th-bg">
                <th>手术及操作编码</th>
                <th>手术及操作日期</th>
                <th>手术级别</th>
                <th>手术及操作名称</th>
                <th style="padding:0">
                    <table class="table table-bordered" style="background:none; margin-bottom:0; border:0">
                        <tr>
                            <th colspan="3">手术及操作医师</th>
                        </tr>
                        <tr>
                            <td>术者</td>
                            <td>Ⅰ助</td>
                            <td>Ⅱ助</td>
                        </tr>
                    </table>
                </th>
                <th>切口愈合等级</th>
                <th>麻醉方式</th>
                <th>麻醉医师</th>
                </thead>
                <tbody>
                <tr>
                    <td>${bingan.ssjczbm1}</td>
                    <td>${bingan.ssjczrq1}</td>
                    <td>${bingan.ssjb1}</td>
                    <td>${bingan.ssjczmc1}</td>
                    <td style="padding:0">
                        <table class="table table-bordered"
                               style="background:none; margin-bottom:0; border:0">
                            <tr>
                                <td>${bingan.sz1}</td>
                                <td>${bingan.yz1}</td>
                                <td>${bingan.ez1}</td>
                            </tr>
                        </table>
                    </td>
                    <td>${bingan.qkyhlb1}</td>
                    <td>${bingan.mzfs1}</td>
                    <td>${bingan.mzys1}</td>
                </tr>
                <tr>
                    <td>${bingan.ssjczbm2}</td>
                    <td>${bingan.ssjczrq2}</td>
                    <td>${bingan.ssjb2}</td>
                    <td>${bingan.ssjczmc2}</td>
                    <td style="padding:0">
                        <table class="table table-bordered"
                               style="background:none; margin-bottom:0; border:0">
                            <tr>
                                <td>${bingan.sz2}</td>
                                <td>${bingan.yz2}</td>
                                <td>${bingan.ez2}</td>
                            </tr>
                        </table>
                    </td>
                    <td>${bingan.qkyhlb2}</td>
                    <td>${bingan.mzfs2}</td>
                    <td>${bingan.mzys2}</td>
                </tr>
                <tr>
                    <td>${bingan.ssjczbm3}</td>
                    <td>${bingan.ssjczrq3}</td>
                    <td>${bingan.ssjb3}</td>
                    <td>${bingan.ssjczmc3}</td>
                    <td style="padding:0">
                        <table class="table table-bordered"
                               style="background:none; margin-bottom:0; border:0">
                            <tr>
                                <td>${bingan.sz3}</td>
                                <td>${bingan.yz3}</td>
                                <td>${bingan.ez3}</td>
                            </tr>
                        </table>
                    </td>
                    <td>${bingan.qkyhlb3}</td>
                    <td>${bingan.mzfs3}</td>
                    <td>${bingan.mzys3}</td>
                </tr>
                <tr>
                    <td>${bingan.ssjczbm4}</td>
                    <td>${bingan.ssjczrq4}</td>
                    <td>${bingan.ssjb4}</td>
                    <td>${bingan.ssjczmc4}</td>
                    <td style="padding:0">
                        <table class="table table-bordered"
                               style="background:none; margin-bottom:0; border:0">
                            <tr>
                                <td>${bingan.sz4}</td>
                                <td>${bingan.yz4}</td>
                                <td>${bingan.ez4}</td>
                            </tr>
                        </table>
                    </td>
                    <td>${bingan.qkyhlb4}</td>
                    <td>${bingan.mzfs4}</td>
                    <td>${bingan.mzys4}</td>
                </tr>
                <tr>
                    <td>${bingan.ssjczbm5}</td>
                    <td>${bingan.ssjczrq5}</td>
                    <td>${bingan.ssjb5}</td>
                    <td>${bingan.ssjczmc5}</td>
                    <td style="padding:0">
                        <table class="table table-bordered"
                               style="background:none; margin-bottom:0; border:0">
                            <tr>
                                <td>${bingan.sz5}</td>
                                <td>${bingan.yz5}</td>
                                <td>${bingan.ez5}</td>
                            </tr>
                        </table>
                    </td>
                    <td>${bingan.qkyhlb5}</td>
                    <td>${bingan.mzfs5}</td>
                    <td>${bingan.mzys5}</td>
                </tr>

                </tbody>
            </table>
            dsafdsafsadfsda
            <table class="table table-hover table-bordered txt_lg">
                <tbody>
                <tr>
                    <td>离院方式：</td>
                    <td colspan="3"><label class="pot-name">${bingan.lyfs}</label>
                        <!--
                        1.医嘱离院 2.医嘱转院，拟接收医疗机构名称：一
                        3.医嘱转社区卫生服务机构/乡镇卫生院，拟接收医疗机构名称：一 4.非医嘱离院 5.死亡 9.其他
                        -->
                    </td>
                </tr>
                <tr>
                    <td>是否有出院31天内再住院计划：</td>
                    <td><label class="pot-name">${bingan.sfzzyjh}</label>
                        <!--
                        1.无 2.有
                        -->
                    </td>
                    <td> 目的：</td>
                    <td>${bingan.md}</td>
                </tr>
                <tr>
                    <td>颅脑损伤患者昏迷时间：</td>
                    <td colspan="3">
                        <!--
                        入院前一天一小时一分钟 入院后一天一小时一分钟
                        -->
                        </td>
                </tr>
                <tr>
                    <td>住院费用（元）：</td>
                    <td colspan="3">总费用${bingan.zfy} （自付金额：－）</td>
                </tr>
                <tr>
                    <td>1. 综合医疗服务类：其他费用：-</td>
                    <td colspan="3">（1）一般医疗服务费：${bingan.ylfuf}  （2）一般治疗操作费：${bingan.zlczf}  （3）护理费：${bingan.hlf}  （4）其他费用：-</td>
                </tr>
                <tr>
                    <td>2. 诊断类：</td>
                    <td colspan="3"> （5）病理诊断费：${bingan.blzdf} （6）实验室诊断费：${bingan.syszdf} （7）影像学诊断费：${bingan.yxxzdf}  （8）临床诊断项目费：${bingan.lczdxmf} </td>
                </tr>
                <tr>
                    <td>3. 治疗类：</td>
                    <td colspan="3"> （9）非手术治疗项目费：-（临床物理治疗费：-）（10）手术治疗费：${bingan.sszlf} （麻醉费：${bingan.maf} 手术费：${bingan.ssf}）</td>
                </tr>
                <tr>
                    <td>4. 康复类：</td>
                    <td colspan="3"> （11）康复费：-</td>
                </tr>
                <tr>
                    <td>5. 中医类：</td>
                    <td colspan="3"> （12）中医治疗费：-</td>
                </tr>
                <tr>
                    <td>6. 西药类：</td>
                    <td colspan="3"> （13）西药费：${bingan.xyf}（抗菌药物费：-）</td>
                </tr>
                <tr>
                    <td>7. 中药类：</td>
                    <td colspan="3"> （14）中成药费：${bingan.zcyf} （15）中草药费：${bingan.cyf}</td>
                </tr>
                <tr>
                    <td>8. 血液与血液制品类：</td>
                    <td colspan="3"> （16）血费：${bingan.xf} （17）白蛋白类制品费：- （18）球蛋白类制品费：- （19）凝血因子类制品费：- （20）细胞因子类制品费：-</td>
                </tr>
                <tr>
                    <td>9. 耗材类：</td>
                    <td colspan="3"> （21）检查用一次性医用材料费：${bingan.jcf}  （22）治疗用一次性医用材料费：-（23）手术用一次性医用材料费：-</td>
                </tr>
                <tr>
                    <td>10. 其他类：</td>
                    <td colspan="3"> （24）其他费：${bingan.qtf}</td>
                </tr>
                </tbody>
            </table>
            <hr>
            <div>
                <label>说明</label>
                <p>（一）医疗付费方式 1.城镇职工基本医疗保险 2.城镇居民基本医疗保险 3.新型农村合作医疗 4.贫困救助 5.商业医疗保险 6.全公费 7.全自费 8.其他社会保险
                    9.其他</p>
            </div>
        </div>
    </div>
</div>

