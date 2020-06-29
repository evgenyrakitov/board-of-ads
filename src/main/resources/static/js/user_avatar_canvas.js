let user_avatar_canvas = {

    drawAvatar: function () {
        let colour_green = "#9bce5f";
        let colour_gray = "#dfdfdf"
        let canvas = document.getElementById("user_avatar_canvas");
        let name = $(canvas).attr("user_name");
        let initials = name.charAt(0).toUpperCase();

        let colourIndex = 0;
        let context = canvas.getContext("2d");

        let canvasWidth = canvas.width,
            canvasHeight = canvas.height,
            canvasCssWidth = canvasWidth,
            canvasCssHeight = canvasHeight;

        let centerX = canvas.width / 2;
        let centerY = canvas.height / 2;
        let radius = canvas.width / 2;

        if (window.devicePixelRatio) {
            $(canvas).attr("width", canvasWidth * window.devicePixelRatio);
            $(canvas).attr("height", canvasHeight * window.devicePixelRatio);
            $(canvas).css("width", canvasCssWidth);
            $(canvas).css("height", canvasCssHeight);
            context.scale(window.devicePixelRatio, window.devicePixelRatio);
        }

        context.beginPath();
        context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
        context.fillStyle = colour_green;
        context.fill();
        context.lineWidth = 2;
        context.strokeStyle = colour_gray;
        context.stroke();
        context.fillStyle = "#FFF";
        context.font = "50px Arial";
        context.textAlign = "center";
        context.fillText(initials, canvasCssWidth / 2, canvasCssHeight / 1.5);
    }
}