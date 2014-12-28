/**
 * 
 */
$(function() {
  var html = $(".error-page-errors").html();
  var words = ["mathtabolism", "null", "exception"];
  var regex = RegExp(words.join("|"), "gi");
  $(".error-page-errors").html(html.replace(regex, "<strong>$&</strong>"))
});