$(function () {
	$(window).on("beforeunload", function () {
	  $(window).scrollTop(0);
	});
  if ($("body").hasClass("landing")) {
    $(window).scroll(function () {
      var windowScroll = $(window).scrollTop();
      var windowHeight = $(document).height() - $(window).height();
      if (windowScroll <= 500) {
        $(".landing .section01").css("opacity", 1 - (windowScroll / 1000) * 2);
        $(".landing .section02").css("opacity", (windowScroll / 1000) * 2);
      }
      if (windowScroll >= 700) {
        $(".landing .section02 .writers .first-row")
          .removeClass("slideDown")
          .addClass("slideUp");
      } else {
        $(".landing .section02 .writers .first-row")
          .removeClass("slideUp")
          .addClass("slideDown");
      }
      if (windowScroll >= 900) {
        $(".landing .section02 .writers .second-row")
          .removeClass("slideDown")
          .addClass("slideUp");
      } else {
        $(".landing .section02 .writers .second-row")
          .removeClass("slideUp")
          .addClass("slideDown");
      }
      if (windowScroll == windowHeight) {
        $(".section03 .meet").css({ bottom: "10px", opacity: "1" });
      } else {
        $(".section03 .meet").css({ bottom: "-10px", opacity: "0" });
      }
    });
  }
});
