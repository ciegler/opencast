angular.module('adminNg.services')
.factory('NewSeriesTheme', ['NewSeriesThemeResource', function (NewSeriesThemeResource) {
    var Theme = function () {
        var me = this;

        this.isValid = function () {
            return true;
        };

        this.reset = function () {
            me.ud = {};
            me.ud.theme = {};
        };

        this.reset();

        this.themeSave = function () {
            me.ud.themeDescription = me.themes[me.ud.theme].description;
        };

        me.themes = NewSeriesThemeResource.get();
    };

    return new Theme();
}]);
