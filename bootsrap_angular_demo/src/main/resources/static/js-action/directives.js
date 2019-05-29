actionApp.directive('datePicker', function () {//1定义一个指令名为datePicker。
    return {
        restrict: 'AC', //2 限制为属性指令和样式指令。③使用link方法来定义指令，在link方法内可使用当前scope、当前元素及元素属性。
        link: function (scope, elem, attrs) { //3初始化jqueryui的datePicker（jquery的写法是$（‘#id’）.datePicker（））。
            elem.datepicker();//4
        }
    };
});