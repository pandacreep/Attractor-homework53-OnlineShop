<#import "../base.ftlh" as base>

<@base.layout>
    <h1>Creating order</h1>
    <br>
    <table>
        <tr>
            <th>ItemId</th>
            <th>Item Name</th>
            <th>Category</th>
            <th>Producer</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <#list items as key, value>
            <tr>
                <td>${value.id}</td>
                <td>${value.name}</td>
                <td>${value.category.name}</td>
                <td>${value.producer.name}</td>
                <td>${value.price}</td>
                <td>${basket.itemsQty[key]}</td>
            </tr>
        </#list>
    </table>
    <br>
    <p>Order total amount: ${amount}</p>
    <br>
    <form action="/addOrder" method="post">
        <fieldset>
            <div class="form-element">
                <label for="name">Full Name</label>
                <input type="text" name="orderReceiverName" id="name" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="phoneNumber">Phone Number</label>
                <input type="number" name="phoneNumber" id="phoneNumber" required>
            </div>
            <br>
            <div class="form-element">
                <label for="email">E-mail</label>
                <input type="text" name="email" id="email" required>
            </div>
            <br>
            <div class="form-element">
                <label for="address">Delivery address</label>
                <input type="text" name="address" id="address">
            </div>
            <br>
            <div class="form-element">
                <label for="paymentType">Type of payment</label>
                <select name="paymentType" id="paymentType">
                    <option value="cash">Cash</option>
                    <option value="visa">Visa card</option>
                    <option value="kaspi">Kaspi QR</option>
                </select>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Create</button>
            </div>
        </fieldset>
    </form>
</@base.layout>