<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Details information for category</h1>
    <form action="/detailsCategory" method="post">
        <fieldset>
            <p>Id: <i>${category.id}</i></p>
            <p>Name: <i>${category.name}</i></p>
            <p>Price: <i>${category.description}</i></p>
        </fieldset>
    </form>
    <p>return back to the list of <a href="/categories">categories</a></p>
</@base.layout>