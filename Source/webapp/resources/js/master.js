/**
 * 
 */
var ESC_KEY = 27;
$(function() {
  $(".dialog-toggle").click(function() {
    var dialog = $("#" + $(this).data("dialog"));
    openDialog(dialog);
  });
  
  $(".dialog-overlay").click(function() {
    closeDialog($(this).parent());
  });
  
  $("#account-submenu").click(function(event) {
    event.stopPropagation();
    toggleAccountSubmenu();
  });
  
  $("html").click(function() {
    closeAccountSubmenu();
  })
  
  $(document).keyup(function(e) {
    if(e.which === ESC_KEY) {
      closeDialog($(".dialog.dialog-open"));
      closeAccountSubmenu();
    }
  });
});

function openDialog(dialog) {
  dialog.addClass("dialog-open");
  dialog.addClass("dialog-open");
}

function closeDialog(dialog) {
  dialog.removeClass("dialog-open");
  dialog.addClass("dialog-close");
  
  dialog.one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function() {
    dialog.removeClass("dialog-close");
  });
}

function toggleLoginSignupForm() {
  $("#login-form").toggleClass("signup-visible");
  $("#signup-form").toggleClass("signup-visible");
}

function toggleAccountSubmenu() {
  var submenu = $("#account-submenu")
  submenu.toggleClass("open");
  if(submenu.hasClass("open")) {
    var arrow = $("#account-submenu-arrow");
    arrow.removeClass("fa-angle-double-down");
    arrow.addClass("fa-angle-double-up");
  } else {
    var arrow = $("#account-submenu-arrow");
    arrow.removeClass("fa-angle-double-up");
    arrow.addClass("fa-angle-double-down");
  }
}

function closeAccountSubmenu() {
  var submenu = $("#account-submenu");
  if(submenu.hasClass("open")) {
    submenu.removeClass("open");
    var arrow = $("#account-submenu-arrow");
    arrow.removeClass("fa-angle-double-up");
    arrow.addClass("fa-angle-double-down");
  }
}