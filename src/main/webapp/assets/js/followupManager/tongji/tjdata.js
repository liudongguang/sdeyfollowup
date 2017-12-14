jQuery(document).ready(function () {
   $("[id^=trID_]").click(function () {
      var ksName=$(this).attr("id").split("_")[1];
       jumpPageNoAuthority("/sdeyfollowup/getOutHosMembers?ksid="+ksName,$("#mainContent"));
   });
});