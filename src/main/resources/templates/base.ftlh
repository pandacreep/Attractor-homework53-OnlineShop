<#macro layout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Shop</title>
    <link href="/css/style.css" rel="stylesheet">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_flex">
            <div class="header__menu">
                <a href="/" class="menu__item">main</a>
                <a href="/items" class="menu__item">items</a>
                <a href="/producers" class="menu__item">producers</a>
                <a href="/categories" class="menu__item">categories</a>
                <a href="/basket" class="menu__item">basket</a>
                <a href="/orders" class="menu__item">orders</a>
                <a href="/login" class="menu__item">login</a>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <#nested/>
</div>
</body>
</html>
</#macro>