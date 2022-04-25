<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Editing item</h1>
    <form action="/editItem" method="post">
        <fieldset>
            <div class="form-element">
                <input type="hidden" name="itemId" value="${item.id}">
            </div>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="${item.name}" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" value="${item.price}" required>
            </div>
            <br>
            <div class="form-element">
                <label for="description">Description</label>
                <input type="text" name="description" id="description" value="${item.description}" required>
            </div>
            <p>Producer: <i>${item.getProducer().name}</i></p>
            <p>Category: <i>${item.getCategory().name}</i></p>
            <div class="form-element">
                <button class="action-button" type="submit">Edit</button>
            </div>
        </fieldset>
    </form>
</@base.layout>