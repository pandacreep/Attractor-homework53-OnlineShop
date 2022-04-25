<#import "../base.ftlh" as base>

<@base.layout>
    <h1>List of orders</h1>
    <br>
    <ul>
        <#list orders as order>
            <hr>
            <ol>
                User email: <i>${order.emailUser}; </i>
                Time: <i>${order.dateTime}; </i>
                <br>
                Receiver: <i>${order.orderReceiverName}; </i>
                Receiver email: <i>${order.emailReceiver}; </i>
                Order amount: <i>${order.orderAmount} </i>
                <br>
                <ul>
                    <#if order.items??>
                        <#list order.items as key, value>
                            <ol>
                                Item: <i>${value.name}; </i>
                                Category: <i>${value.category.name}; </i>
                                Product: <i>${value.producer.name}; </i>
                                Price: <i>${value.price}; </i>
                                Qty: <i>${order.itemsQty[key]} </i>
                            </ol>
                        </#list>
                    </#if>
                </ul>
            </ol>
        </#list>
    </ul>
    <br>
</@base.layout>