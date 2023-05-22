const setUpCsrfToken = () => {
    const token = $('meta[name="_csrf"]').attr('content');
    const header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}
