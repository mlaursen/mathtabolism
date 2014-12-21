/**
 * 
 */

var unitSystem = $("#unit-system");

unitSystem.change(function() {
  var heightLargeUnit = $("#height-large-unit");
  var heightSmallUnit = $("#height-small-unit");
  var weightUnit = $("#weight-unit");
  var val = $(this).html();
  var unit1, unit2, unit3;
  switch(val) {
    case 'Imperial':
      unit1 = 'ft';
      unit2 = 'in';
      unit3 = 'lbs'
      break;
    case 'Metric':
      unit1 = 'm';
      unit2 = 'cm';
      unit3 = 'kg';
      break;
  }
  
  heightLargeUnit.attr("class", unit1);
  heightSmallUnit.attr("class", unit2);
  weightUnit.attr("class", unit3);
});