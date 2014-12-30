/**
 * 
 */
$(function() {
  addEventListeners();
});

function addEventListeners() {
  if(additionalEventListeners != undefined && typeof additionalEventListeners == 'function') {
    additionalEventListeners()
  }
  $(".nl-form .form-field").each(function() {
    var mainDiv = this;
    var hidden = $(this).find("input[type='hidden']");
    var toggle = $(this).find(".form-field-toggle");
    $(toggle).click(function() {
      $(mainDiv).addClass("form-field-open");
      $("#form-field-overlay").addClass("open");
    });
    
    $(mainDiv).find($("ul > li")).each(function() {
      $(this).click(function() {
        updateSelected(mainDiv, $(this));
        var val = $(this).data("value");
        hidden.val(val);
        toggle.html($(this).html());
        toggle.change();
        $(mainDiv).removeClass("form-field-open");
        $("#form-field-overlay").removeClass("open");
      });
    });
  
  
    $("#form-field-overlay").click(function() {
      $(mainDiv).removeClass("form-field-open");
      $(this).removeClass("open");
    });
  });
}

function updateSelected(mainDiv, toSelect) {
  $(mainDiv).find($("ul > li")).each(function() {
    if($(this).get(0) !== toSelect.get(0)) {
      $(this).removeClass("dd-selected");
    } else {
      $(this).addClass("dd-selected");
    }
  });
}