$(function() {
  $("[data-toggle=modal]").each(function() {
    $(this).click(function() {
      var id = $(this).data("target");
      $(id).addClass("modal-overlay");
      $(id).show();
    });
  });
  
  $("[data-dismiss=modal]").each(function() {
    $(this).click(function() {
      $(".modal").hide();
    });
  });
});