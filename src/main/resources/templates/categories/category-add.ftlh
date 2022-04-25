<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Adding new Category</h1>
    <form action="/addCategory" method="post">
        <fieldset>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="description">Description</label>
                <input type="text" name="description" id="description" required>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Add</button>
            </div>
        </fieldset>
    </form>
</@base.layout>