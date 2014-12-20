/**
 * 
 */
var form = $("#signup-form");
var user = $("input[id$=':username']");
var pswd = $("input[id$=':password']");
var pswdErr = $("#password-error");
var pswdImg = $("img[id$=':checklist-password']");
var conf = $("input[id$=':confirm-password']");
var confErr = $("#confirm-password-error");
var confImg = $("img[id$=':checklist-confirm-password']");
form.submit(function() {
  var isUsernameSet = user.val() != '';
  return isUsernameSet && isMatchingPasswords() && pswd.val().length > 1;
});

function isStrongPassword() {
  var v = pswd.val();
  console.log(v);
  return v.match(/^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$/);
}

function isMatchingPasswords() {
  if(pswd.val() !== conf.val()) {
    if(!pswdErr.hasClass("visible")) {
      pswdErr.addClass("visible");
    }
    
    if(!confErr.hasClass("visible")) {
      confErr.addClass("visible");
    }
    pswdErr.html(passwordMatchError);
    confErr.html(passwordMatchError);
    
    pswdImg.attr("src", cross);
    confImg.attr("src", cross);
    
    if(pswdImg.hasClass("hidden")) {
      pswdImg.removeClass("hidden");
      confImg.removeClass("hidden");
    }
    return false;
  } else {
    pswdErr.removeClass("visible");
    pswdErr.html("");
    confErr.removeClass("visible");
    confErr.html("");
    pswdImg.attr("src", checkmark);
    confImg.attr("src", checkmark);
    return true;
  }
}

conf.keyup(function() {
  isMatchingPasswords();
});