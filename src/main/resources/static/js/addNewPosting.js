
$(document).ready(function () {
    $("#btnTransport").click(function(){
        var el = document.getElementById('transport');
        // el.style.display === 'none' ? el.d = 'block' : el.d.style.display= 'none';
        el.getAttribute("display") ==="none"? el.setAttribute("display","block") : el.setAttribute("display","none");
    });
});
