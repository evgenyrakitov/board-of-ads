const user_avatar_canvas = {

    drawAvatar: function () {
        let colour_green = "#9bce5f";
        let colour_gray = "#dfdfdf"
        let canvas = document.getElementById("user_avatar_canvas");
        let name = $(canvas).attr("user_name");
        let initials = name.charAt(0).toUpperCase();
        let context = canvas.getContext("2d");
        let photoImg = document.getElementById("user_photo_image");

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

        if (photoImg.getAttribute("src") === "") {  // draw only circle with first name letter.
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
        } else {
            context.save();
            context.beginPath();
            context.arc(centerX, centerY, radius, 0, Math.PI * 2, true);
            context.closePath();
            context.clip();

            context.drawImage(photoImg, 0, 0, canvasWidth, canvasHeight);

            context.beginPath();
            context.arc(0, 0, radius, 0, Math.PI * 2, true);
            context.clip();
            context.closePath();
            context.restore();

            context.beginPath();
            context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
            context.lineWidth = 2;
            context.strokeStyle = colour_gray;
            context.stroke();
        }
    }
};