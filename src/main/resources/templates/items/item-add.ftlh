<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Adding new Item</h1>
    <form action="/addItem" method="post" enctype="multipart/form-data">
        <fieldset>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="producer">Producer</label>
                <select name="producerId" id="producer">
                    <#list producers?keys as key>
                        <option value="${key}">${producers[key]}</option>
                    </#list>
                </select>
                <a href="/producer-add"> add new producer </a>
            </div>
            <br>
            <div class="form-element">
                <label for="category">Category</label>
                <select name="categoryId" id="category">
                    <#list categories?keys as key>
                        <option value="${key}">${categories[key]}</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-element">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" required>
            </div>
            <br>
            <div class="form-element">
                <label for="description">Description</label>
                <input type="text" name="description" id="description" required>
            </div>
            <br>
            <div class="form-element">
                <label for="file">Image</label>
                <input type="file" name="file" id="file" accept="image/*">
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Add</button>
            </div>
        </fieldset>
    </form>
</@base.layout>