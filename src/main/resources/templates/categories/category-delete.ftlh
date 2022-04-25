<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Deleting category</h1>
    <form action="/deleteCategory" method="post">
        <fieldset>
            <h2>Are you sure, you want to delete category?</h2>
            <div class="form-element">
                <input type="hidden" name="categoryId" value="${category.id}">
            </div>
            <p>Name: <i>${category.name}</i></p>
            <p>Description: <i>${category.description}</i></p>
            <#if categoryUsedInItems>
                <p>Actually this category is used in Items. Please delete all corresponding items first</p>
            <#else>
                <div class="form-element">
                    <button class="action-button" type="submit">Delete</button>
                </div>
            </#if>
        </fieldset>
    </form>
    <#if categoryUsedInItems>
        <p><a href="/categories">return back</a></p>
    <#else>
        <p>Please confirm! or <a href="/categories">return back</a></p>
    </#if>
</@base.layout>