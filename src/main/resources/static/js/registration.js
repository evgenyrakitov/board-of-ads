export function passwordExist (word) {
    if (word.length > 0) {
        return true;
    }
    else {
        return false;
    }
}

export function passwordEquals (password, password_confirm) {
    if(password === password_confirm) {
        return true;
    }
    else {
        return false;
    }
}

export function checker(word, regexpRow) {
    var re = new RegExp(regexpRow);
    return re.test(word);
}

export function summator(password) {
    //здесь мы проверяем, сложность пароля. последовательно регулярками смотрим, есть ли в нём большая буква, цифра и тд.
    //при обнаружении оной увеличиваем значение summ на 1. минимальный порог сложности для пароля - 2. (наличие букв и цифр)
    var summ = 0;
    var regexpArray = ["[A_Z]", "\\d", "\\W", "\\w"];    //большая латинская, цифра, не_буква, малая_буква.

    regexpArray.forEach(function (item, index, array) {
        if(checker(password, item) === true) {
            summ++;
        }
    })
    return summ;
}

export function warningField(field) {
    $(field).css("background-color", "red");
}

export function successField(field) {
    $(field).css("background-color", "green");
}

export function infoField(field) {
    $(field).css("background-color", "yellow");
}

export function save(login, password, first_name, last_name, phone) {
    let url = "/rest/admin/add";
    let type = "POST";
    let data = {
        user: {
            email: login,
            password: password,
            firstName: first_name,
            lastName: last_name,
            phone: phone,
        },
    };

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        cache: false,
        data: JSON.stringify(data.user)
    });
}
