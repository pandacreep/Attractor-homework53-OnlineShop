<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Showing all orders for user <b>${userId}</b></h1>
    <form action="/showOrders" method="post">
        <fieldset>
            <p>To see all your orders type your e-mail <b>${userId}</b></p>
            <p>To see orders of another user you need to relogin</p>
            <div class="form-element">
                <input type="hidden" name="userId" value="${userId}">
            </div>
            <div class="form-element">
                <input type="text" name="checkText">
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Show</button>
            </div>
        </fieldset>
    </form>
</@base.layout>