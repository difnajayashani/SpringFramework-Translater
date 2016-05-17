
function validatePassword(fld) {
    var error = "";
    var illegalChars = /[\W_]/; // allow only letters and numbers

    if (fld.value == "") {
        fld.style.background = 'Yellow';
        error = "You didn't enter a password.\n";
        alert(error);
        return false;

    } else if ((fld.value.length < 7) || (fld.value.length > 15)) {
        error = "The password is the wrong length. \n";
        fld.style.background = 'Yellow';
        alert(error);
        return false;

    }  else if ( (fld.value.search(/[a-zA-Z]+/)==-1) || (fld.value.search(/[0-9]+/)==-1) ) {
        error = "The password must contain at least one numeral.\n";
        fld.style.background = 'Yellow';
        alert(error);
        return false;

    } else {
        fld.style.background = 'White';
    }
    return true;
}



<!--javascript to check two passwords are equal -->

    function passwordsEqual(fld1, fld2) {
        var error2 = "";

        if (fld1.value == "") {
            fld1.style.background = 'Yellow';
            error2 = "You have to confirm the password.\n";
            alert(error2);
            return false;

        }

        else if ((fld1.value) != (fld2.value)) {
            error = "Two passwords have to be Equal. \n";
            fld1.style.background = 'Yellow';
            alert(error);
            return false;

        } else {
            fld1.style.background = 'White';
        }
        return true;
    }
