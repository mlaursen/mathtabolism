/**
 * 
 */
$(function() {
  $(".dialog-toggle").click(function() {
    var id = $(this).data("dialog");
    console.log(id);
    
    $("#" + id).toggleClass("dialog-open");
  });
  
  $(".dialog-overlay").click(function() {
    $(this).parent().removeClass("dialog-open");
  });
});