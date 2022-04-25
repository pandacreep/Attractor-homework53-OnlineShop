<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Items in the basket for ${basket.id}</h1>
    <p>You can <a class="as-button" href="/items">add</a> other items by selecting <b>buy</b> action</p>
    <p>You can <a class="as-button" href="/basket/${basket.id}/clear">clear</a> your basket</p>
    <br>
    <table>
        <tr>
            <th>ItemId</th>
            <th>Item Name</th>
            <th>Category</th>
            <th>Producer</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        <#list items as key, value>
            <tr>
                <td>${value.id}</td>
                <td>${value.name}</td>
                <td>${value.category.name}</td>
                <td>${value.producer.name}</td>
                <td>${value.price}</td>
                <td>${basket.itemsQty[key]}</td>
                <td>
                    <a class="as-button" href="/basket/${value.id}/edit">Edit</a>
                    <a class="as-button" href="/basket/${value.id}/delete">Delete</a>
                </td>
            </tr>
        </#list>
    </table>
    <br>
    <p>Having all products added please make <a class="as-button" href="/orders/create">order</a></p>
</@base.layout>