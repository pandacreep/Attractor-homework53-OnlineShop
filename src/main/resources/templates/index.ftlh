<#import "base.ftlh" as base>

<@base.layout>
    <h1>Welcome to our Online Shop!</h1>
    <ul>
        <li>You can see list of all <a class="as-button" href="/items">items</a></li>
        <br>
        <li>You can see list of all <a class="as-button" href="/producers">producers</a></li>
        <br>
        <li>You can see list of all <a class="as-button" href="/categories">categories</a></li>
        <br>
        <li>You can see your <a class="as-button" href="/basket">basket</a></li>
        <br>
        <li>You can see your <a class="as-button" href="/orders">orders</a></li>
        <br>
        <li>You can <a class="as-button" href="/login">login</a> (or change it) </li>
    </ul>
    <p>Important! To work with basket or orders you need to be logged on!</p>
</@base.layout>