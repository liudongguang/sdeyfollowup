jQuery(document).ready(function () {
    var jq_content=$("#mainContent");
    initTable(jq_content);
    console.log($("[mtips]"))
    $("[mtips]").mouseover(function () {
        var jq_this=$(this);
        layer.tips(jq_this.attr("mtips"), "#"+jq_this.attr('id'), {
            tips: [1, '#3595CC'],
            time: 3000
        });
    });
});