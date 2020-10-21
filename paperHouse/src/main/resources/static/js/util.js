$(function () {
  // header hamberger menu mobile action
  $("header .toggle").on("click", function () {
    $(".mb-header-menu").css("right", "0");
    $("header .mb-bg").fadeIn(200);
  });
  $("header .close").on("click", function () {
    $(".mb-header-menu").css("right", "-100%");
    $("header .mb-bg").fadeOut(200);
  });
  // unbind scroll action when hamberger menu is ON
  $(".mb-bg, .mb-header-menu").on("scroll touchmove mousewheel", function (event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
  });

  // myPage and myPageSub minHeightAuto
  let headerHeight = $("header").outerHeight();
  let footerHeight = $("footer").outerHeight();
  if ($(".myPage").length > 0) {
    $(".myPage").css("min-height", "calc(100vh - " + (headerHeight + footerHeight) + "px)");
  } else if ($(".myPageSub").length > 0) {
    let innerTopHeight = $(".inner-top").outerHeight();
    $(".myPageSub").css("min-height", "calc(100vh - " + (headerHeight + innerTopHeight + footerHeight) + "px)");
  }
});
