export default class {}

function passwordExist (word) {
    if (word.length > 0) {
        return true;
    }
    else {
        console.log("password is empty. Fixed this")
        return false;
    }
}

function passwordEquals (password, password_confirm) {
    if(password === password_confirm) {
        return true;
    }
    else {
        console.log("Passwords not equal!");
        return false;
    }
}

function checker(password, regexpRow) {
    var re = new RegExp(regexpRow);
    var myArray = re.exec(password);
    try {
        myArray.length > 0;
        return 1;
    } catch (e) {
       return 0;
    }
}

function summator(arr) {
    let summ = 0;
    for (var i = 0; i < arr.length; i++) {
        let tmp = arr[i];
        if (tmp === 1) {
            summ += tmp;
        }
    }
    return summ;
}

function warningPass(field) {
    $(field).css("background-color", "red");
}

function save(login, password) {
    let url = "/rest/add";
    let type = "POST";
    let data = {
        user: {
            login: login,
            password: password
        },
    };

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        cache: false,
        data: JSON.stringify(data.user)
        }).always(alert("я схоронил юзера!"))
}

function dd () {
    let login = $("#login").val();
    let password = $("#password").val();
    let password_confirm = $("#password_confirm").val();
    let resultArray = new Array();
    let sum;

    //========= test1 - is field not empty =========//
    if (passwordExist(password) && passwordExist(password_confirm) == true) {
        console.log("оба пароля существуют!");
    }
    else {
        warningPass("#password");
        warningPass("#password_confirm");
        $("#password_confirm").css('background-color', 'red');
        alert("увы, не введён пароль...");
        resultArray.push(false);
        exit(1);
    }

    //============= test2 if password equals password_confirm ==========//
    if (passwordEquals(password, password_confirm)) {
        console.log("пароли совпадают");
    }
    else {
        resultArray.push(false);    //1
        warningPass("#password");
        warningPass("#password_confirm");
        alert("пароли не совпадают!");
        // exit(1);
    }

    //===================== test 3 - minimal legth ==========//
    if (password.length < 5) {
        console.log("your password is short!");
        alert("your password is short");
        exit(1);
    }
    //================ test's content upper symvol, ditit and extends symbol  ===============//
    resultArray.push(checker(password, "[A-Z]"));   //2

    resultArray.push(checker(password, "\\d")); //3

    resultArray.push(checker(password, "\\w")); //4

    resultArray.push(checker(password, "\\W")); //5

    // add exit(1) if length password < 5 symbols

    sum = summator(resultArray);
    alert(sum);
    if (sum < 2) {
        alert("don't accepr password. is very simple!");
    }
    else {
        console.log("пробуем сохранить...");
        save(login, password);
    }
}

