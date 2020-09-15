function setCookie(cname, cvalue, exdays)
{
      var d = new Date();
      d.setTime(d.getTime() + (exdays*24*60*60*1000));
      var expires = "expires="+ d.toUTCString();
      document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname)
{
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++)
    {
        var c = ca[i];
        while (c.charAt(0) == ' ')
        {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0)
        {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function printFeed(JSON)
{
    //for each ... finish this up tommo with the printing the list of posts from database
}


function validPassword()
{
    pwd1 = document.accountregister.pwd1;
    pwd2 = document.accountregister.pwd2;

    if (pwd1 != pwd2)
    {
        return
    }
}

function checkLogIn()
{

}

function radioCheck()
{
    var radio = document.getElementByName("type");

    for (var i = 0, length = radios.length; i < length; i++) {
        if (radio[i].checked)
        {
            if (radio[i].value=="uni")
            {
                document.getElementById("url").href = "/creategroup/university";
                break;
            }
            if (radio[i].value=="business")
            {
                document.getElementById("url").href = "/creategroup/business";
                break;
            }
            if (radio[i].value=="club")
            {
                document.getElementById("url").href = "/creategroup/club";
                break;
            }
            // only one radio can be logically checked, don't check the rest

        }
    }
}
