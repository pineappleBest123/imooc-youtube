
# Promise
Promise是JavaScript中处理异步操作的一种模式。它是一种代表异步操作最终完成或失败的对象。
使用 Promise，可以更容易、更清晰地处理异步代码和提供更强大的错误处理机制。

Promise 对象具有三个状态：

1、Pending（进行中）: 初始状态，表示异步操作正在进行中。
2、Fulfilled（已成功）: 表示异步操作成功完成，此时会调用 then 方法。
3、Rejected（已失败）: 表示异步操作失败，此时会调用 catch 方法。

一个Promise可以从 Pending 状态转变为 Fulfilled 或 Rejected 状态，但状态一旦变化就不可逆。
Promise 的主要方法是 then 和 catch，它们分别用于处理异步操作成功和失败的情况。


    let myPromise = new Promise((resolve, reject) => {
        // 异步操作，例如从服务器获取数据
        let success = true;
        if (success) {
            resolve('成功时的数据');
        } else {
            reject('失败时的原因');
        }
    });
        
    // 处理成功情况
    myPromise.then((data) => {
        console.log('成功:', data);
    })
    // 处理失败情况
    .catch((error) => {
        console.error('失败:', error);
    });

在上述例子中，resolve 和 reject 参数是 Promise 构造函数提供的回调函数。
resolve 用于将 Promise 状态从 Pending 转变为 Fulfilled，
而 reject 则用于将状态从 Pending 转变为 Rejected。






