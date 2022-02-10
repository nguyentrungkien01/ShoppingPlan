(function ($) {
    "use strict";
    var healthcare = {
        initialised: false,
        version: 1.0,
        Solar: false,
        init: function () {

            if (!this.initialised) {
                this.initialised = true;
            } else {
                return;
            }

            // Functions Calling
            this.menu_toggle();
        },

        // menu toggle start
        menu_toggle: function () {
            if ($(".hc-main-header").length > 0) {
                $(".hc-menu-toggle").on('click', function (e) {
                    event.stopPropagation();
                    $(".hc-main-header").toggleClass("hc-open-menu");
                });
                $("body").on('click', function () {
                    $(".hc-main-header").removeClass("hc-open-menu");
                });
                $(".hc-navbar").on('click', function () {
                    event.stopPropagation();
                });
            };
        },
        // menu toggle end

    };
    healthcare.init();
})(jQuery);

// init cursor
var cursors = [{
    cursor_id: "3",
    cursor_type: "0",
    cursor_shape: "15",
    cursor_image: "",
    default_cursor: "auto",
    hover_effect: "plugin",
    body_activation: "1",
    element_activation: "0",
    selector_type: "tag",
    selector_data: "body",
    color: "#cc3a3b",
    width: "30",
    blending_mode: "normal"
}];