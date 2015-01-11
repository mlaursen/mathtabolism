/**
 * 
 */
var CHECK = "fa-check-circle-o";
var TIMES = "fa-times-circle-o";
var RED   = "icon-red";
var GREEN = "icon-green";

function updateUsernameFields(data) {
  if(data.status == 'success') {
    var username = $("input[id$=':username']");
    var usernameError = $("span[id$=':username-error']").html();
    var usernameChecklist = $("#checklist-username");
    if(usernameError != '') {
      if(usernameChecklist.hasClass(CHECK)) {
        usernameChecklist.removeClass(CHECK);
        usernameChecklist.removeClass(GREEN);
      }
      
      if(!usernameChecklist.hasClass(TIMES)) {
        usernameChecklist.addClass(TIMES);
        usernameChecklist.addClass(RED);
      }
    } else {
      if(usernameChecklist.hasClass(TIMES)) {
        usernameChecklist.removeClass(TIMES);
        usernameChecklist.removeClass(RED);
      }
      
      if(!usernameChecklist.hasClass(CHECK)) {
        usernameChecklist.addClass(CHECK);
        usernameChecklist.addClass(GREEN);
      }
    }
  }
}





var form = $("#signup-form");
var user = $("input[id$=':username']");
var userErr = $("span[id$=':username-exists']");
var userImg = $("img[id$=':checklist-username']")
var pswd = $("input[id$=':password']");
var pswdErr = $("#password-error");
var pswdImg = $("img[id$=':checklist-password']");
var conf = $("input[id$=':confirm-password']");
var confErr = $("#confirm-password-error");
var confImg = $("img[id$=':checklist-confirm-password']");

function isValidUsername() {
  if(user.val() == '') {
    addUsernameError(usernameError);
    return false;
  } else {
    return !$("span[id$=':username-exists']").hasClass("visible");
  }
}

function addUsernameError(msg) {
  if(!userErr.hasClass("visible")) {
    userErr.addClass("visible");
  }
  
  if(userImg.hasClass("hidden")) {
    userImg.removeClass("hidden");
  }
  
  userImg.attr("src", cross);
  
  userErr.html(msg);
}

function addPasswordError(msg) {
  if(!pswdErr.hasClass("visible")) {
    pswdErr.addClass("visible");
  }
  
  if(!confErr.hasClass("visible")) {
    confErr.addClass("visible");
  }
  
  if(pswdImg.hasClass("hidden") || confImg.hasClass("hidden")) {
    pswdImg.removeClass("hidden");
    confImg.removeClass("hidden");
  }

  pswdImg.attr("src", cross);
  confImg.attr("src", cross);
  pswdErr.html(msg);
  confErr.html(msg);
}

function removePasswordErrors() {
  pswdErr.removeClass("visible");
  pswdErr.html("");
  confErr.removeClass("visible");
  confErr.html("");
  pswdImg.attr("src", checkmark);
  confImg.attr("src", checkmark);
}

function isValidPassword() {
  if(pswd.val() == '') {
    addPasswordError(passwordError);
    return false;
  } else if(pswd.val() !== conf.val()) {
    addPasswordError(passwordMatchError);
    return false;
  } else {
    removePasswordErrors();
    return true;
  }
}

form.submit(function() {
  var isValid = isValidUsername();
  isValid = isValidPassword() && isValid;
  return isValid;
});

conf.keyup(function() {
  isValidPassword();
});