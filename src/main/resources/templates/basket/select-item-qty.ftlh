<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Selecting item and quantity</h1>
    <form action="/select-item" method="post">
        <fieldset>
            <div class="form-element">
                <input type="hidden" name="userId" value="${userLogged}">
            </div>
            <div class="form-element">
                <input type="hidden" name="itemId" value="${item.id}">
            </div>
            <p>Name: <i>${item.name}</i></p>
            <p>Producer: <i>${item.getProducer().name}</i></p>
            <p>Category: <i>${item.getCategory().name}</i></p>
            <p>Price: <i>${item.price}</i></p>
            <div class="form-element">
                <label for="qty">Quantity</label>
                <input type="number" name="qty" id="qty" value="${qty}" required autofocus>
            </div>
            <br><br>
            <div class="form-element">
                <button class="action-button" type="submit">Select</button>
            </div>
        </fieldset>
    </form>
</@base.layout>