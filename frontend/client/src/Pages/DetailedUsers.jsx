import {useState, useEffect} from 'react';
import './userList.css';

const DetailedUsers = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch("user/all")
            .then((response) => response.json())
            .then((data) => setUsers(data))
            .catch(error => console.error(error))
    }, []);
    
    console.log("ezaz" + users);

    return (
        <div id='userListContainer'>
            <h2 id='title'>Detailed list of active users</h2>
            <ul>
                {users.map((user) => (
                    <li key={user.username}> Username: {user.username}, Date of registration: {user.regDate}, Number of questions posted: {user.noOfQuestions}, Number of answers posted: {user.noOfAnswers}</li>
                ))}
            </ul>
        </div>
    );
}

export default DetailedUsers;