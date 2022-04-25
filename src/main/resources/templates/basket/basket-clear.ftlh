<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Clearing Basket</h1>
    <form action="/clearBasket" method="post">
        <fieldset>
            <h2>Are you sure, you want to clear basket?</h2>
            <p>To submit clearance type <b>${basketId}</b></p>
            <div class="form-element">
                <input type="hidden" name="basketId">
            </div>
            <div class="form-element">
                <input type="text" name="checkText">
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Clear</button>
            </div>
        </fieldset>
    </form>
</@base.layout>