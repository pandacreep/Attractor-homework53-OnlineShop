<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Editing category</h1>
    <form action="/editCategory" method="post">
        <fieldset>
            <div class="form-element">
                <input type="hidden" name="categoryId" value="${category.id}">
            </div>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" value="${category.name}" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="description">Description</label>
                <input type="text" name="description" id="description" value="${category.description}" required>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Edit</button>
            </div>
        </fieldset>
    </form>
</@base.layout>