<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Items in the store</h1>
    <p>You can add new item here <a class="as-button" href="/items/add"> add </a></p>
    <table>
        <tr>
            <th>Name</th>
            <th>Producer</th>
            <th>Category</th>
            <th>Price</th>
            <th>Description</th>
            <th>Average score</th>
            <th>Actions</th>
        </tr>
        <#list items as item>
            <tr>
                <td>${item.name}</td>
                <td>${item.getProducer().name}</td>
                <td>${item.getCategory().name}</td>
                <td>${item.price}</td>
                <td>${item.description}</td>
                <td>${avgScore[item.id]}</td>
                <td>
                    <a class="as-button" href="/items/${item.id}/details">Details</a>
                    <a class="as-button" href="/items/${item.id}/edit">Edit</a>
                    <a class="as-button" href="/items/${item.id}/delete">Delete</a>
                    <a class="as-button" href="/items/${item.id}/buy">Buy</a>
                </td>
            </tr>
        </#list>
    </table>
</@base.layout>