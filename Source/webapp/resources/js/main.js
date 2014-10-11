
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