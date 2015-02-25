/**
 * 
 */
var ESC_KEY = 27;
$(function() {
  $('.dialog-toggle').click(function() {
    var dialog = $('#' + $(this).data('dialog'));
    openDialog(dialog);
  });
  
  $('.dialog-overlay').click(function() {
    closeDialog($(this).parent());
  });
  
  $('.dialog .dialog-close').click(function() {
    closeDialog($('.dialog.dialog-open'));
  })
  
  $('#account-submenu').click(function(event) {
    event.stopPropagation();
    toggleAccountSubmenu();
  });
  
  $('html').click(function() {
    closeAccountSubmenu();
  })
  
  $(document).keyup(function(e) {
    if(e.which === ESC_KEY) {
      closeDialog($('.dialog.dialog-open'));
      closeAccountSubmenu();
    }
  });
});

function openDialog(dialog) {
  dialog.addClass('dialog-open');
  dialog.addClass('dialog-open');
}

function closeDialog(dialog) {
  if(typeof dialog == 'string') {
    dialog = $('#' + dialog);
  }
  dialog.removeClass('dialog-open');
  dialog.addClass('dialog-close');
  
  dialog.one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function() {
    dialog.removeClass('dialog-close');
  });
}

function toggleLoginSignupForm() {
  $('#login-form').toggleClass('signup-visible');
  $('#signup-form').toggleClass('signup-visible');
}

function signupFormVisible(data) {
  updateUsernameFields(data);
  updatePasswordFields(data);
  if(data.status == 'success') {
    $('#signup-form').toggleClass('signup-visible');
  }
}

function toggleAccountSubmenu() {
  var submenu = $('#account-submenu')
  submenu.toggleClass('open');
  if(submenu.hasClass('open')) {
    var arrow = $('#account-submenu-arrow');
    arrow.removeClass('fa-angle-double-down');
    arrow.addClass('fa-angle-double-up');
  } else {
    var arrow = $('#account-submenu-arrow');
    arrow.removeClass('fa-angle-double-up');
    arrow.addClass('fa-angle-double-down');
  }
}

function closeAccountSubmenu() {
  var submenu = $('#account-submenu');
  if(submenu.hasClass('open')) {
    submenu.removeClass('open');
    var arrow = $('#account-submenu-arrow');
    arrow.removeClass('fa-angle-double-up');
    arrow.addClass('fa-angle-double-down');
  }
}

var CHECK = 'fa-check-circle-o';
var TIMES = 'fa-times-circle-o';
var RED   = 'icon-red';
var GREEN = 'icon-green';

/**
 * Changes the checklist field to be a times circle icon from
 * font-awesome. Removes the success icon (if existing). Also changes
 * the color from green to red for error.
 * @param field the checklist field of a font-awesome icon
 */
function addError(field) {
  if(field.hasClass(CHECK)) {
    field.removeClass(CHECK);
    field.removeClass(GREEN);
  }
  
  if(!field.hasClass(TIMES)) {
    field.addClass(TIMES);
    field.addClass(RED);
  }
}

/**
 * Changes the checklist field to be a check circle icon from
 * font-awesome. Removes the error icon (if existing). Also changes
 * the color from red to green for success.
 * @param field the checklist field of a font-awesome icon
 */
function addSuccess(field) {
  if(field.hasClass(TIMES)) {
    field.removeClass(TIMES);
    field.removeClass(RED);
  }
  
  if(!field.hasClass(CHECK)) {
    field.addClass(CHECK);
    field.addClass(GREEN);
  }
}

/**
 * Updates the 'checklist' items for the username.
 * 
 * @param data
 */
function updateUsernameFields(data) {
  if(data.status == 'success') {
    var usernameError = $("span[id$=':username-error']").html();
    var usernameChecklist = $('#checklist-username');
    if(usernameError != '') {
      addError(usernameChecklist);
    } else {
      addSuccess(usernameChecklist);
    }
  }
}

function updatePasswordFields(data) {
  if(data.status == 'success') {
    var password = $("input[id$=':password']");
    var passwordError = $("span[id$=':password-error']");
    var passwordChecklist = $('#checklist-password');
    var passwordConfirm = $("input[id$=':confirm-password']");
    var passwordConfirmError = $("span[id$=':confirm-password-error']").html();
    var passwordConfirmChecklist = $('#checklist-confirm-password');
    
    if(passwordError.html() != '') {
      addError(passwordChecklist);
    } else {
      addSuccess(passwordChecklist);
    }
    if(passwordConfirmError != '') {
      addError(passwordConfirmChecklist);
    } else {
      addSuccess(passwordConfirmChecklist);
    }
  }
}

function verifyAccountInitialization(data) {
  if(data.status != 'success') {
    return;
  }
  
  var isUsingAge = $("input[id$='using-age']").is(":checked");
  var age = $("input[id$='age-field']");
  var birthday = $("input[id$='birthday-field']");
  if(isUsingAge && age.val() != '' || !isUsingAge && birthday.val() != '') {
    setTimeout(function() {
      $(".step-form-completed-redirect").click();
    }, 5000);
  } else {
    addEventListeners();
  }
  
}
