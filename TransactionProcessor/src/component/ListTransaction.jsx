import { useEffect, useState } from "react"

export default function ListTransaction(){

    const [transactions,setTransaction] = useState([]);
     
    useEffect(()=>{
        fetch("http://localhost:8084/transaction").then((response)=> response.json()).then((data)=>{
            console.log(data);
            setTransaction(data);
        })
    },[]);

    return (
    <div>
      <h2>Transaction List</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Location</th>
            <th>Status</th>
            <th>Process Status</th>
            <th>Balance</th>
            <th>Transaction Amount</th>
            <th>Receiver ID</th>
            <th>Tax</th>
            <th>Created At</th>
          </tr>
        </thead>

        <tbody>
          {transactions.map((t) => (
            <tr key={t.transactionId}>
              <td>{t.transactionId}</td>
              <td>{t.location}</td>
              <td>{t.status}</td>
              <td>{t.processStatus === "C" ? "Complete" : "Not Complete"}</td>
              <td>{t.balance}</td>
              <td>{t.transactionAmount}</td>
              <td>{t.receiverId}</td>
              <td>{t.tax}</td>
              <td>{new Date(t.createdAt).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}