<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Deleting item</h1>
    <form action="/deleteItem" method="post">
        <fieldset>
            <h2>Are you sure, you want to delete item?</h2>
            <div class="form-element">
                <input type="hidden" name="itemId" value="${item.id}">
            </div>
            <p>Producer <i>${item.name}</i></p>
            <p>Producer <i>${item.price}</i></p>
            <p>Producer <i>${item.description}</i></p>
            <p>Producer <i>${item.getProducer().name}</i></p>
            <p>Category <i>${item.getCategory().name}</i></p>
            <div class="form-element">
                <button class="action-button" type="submit">Delete</button>
            </div>
        </fieldset>
    </form>
    <p>Please confirm! or <a href="/items">return back</a></p>
</@base.layout>