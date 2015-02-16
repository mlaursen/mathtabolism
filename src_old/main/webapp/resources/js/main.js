
function isBetween(value, min) {
  isBetween(value, min, null);
}

function isBetween(value, min, max) {
  var isMaxed = true;
  if(max != null && max > min) {
    isMaxed = value.length <= max;
  }
  return value != null && value != "" && value.length >= min && isMaxed;
}

$(function () {
  /**
   * Dropdown stuff
   */
  $(".dropdown-container").each(function () {
    var display = $(this).children(".displayed");
    var selected = $(display).children(".selected");
    var hidden = $(this).children("input");
    var ul = $(this).children("ul");
    
    $(display).click(function () {
      ul.fadeIn(200, function() { ul.focus(); });
      // No idea why it is 20pixels off
      ul.width(display.width() + 20);
    });
    
    $(ul).on("blur", function() {
      ul.fadeOut(200);
    });
    
    ul.find("li").click(function () {
      selected.html($(this).html());
      hidden.val($(this).data("value"));
    });
  });
  
});