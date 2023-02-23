import {useState, useEffect} from 'react';

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
        <div>
            <h2>User List</h2>
            <ul>
                {users.map((user) => (
                    <li key={user.username}> Username: {user.username}, Date of registration: {user.regDate}, Number of questions posted: {user.noOfQuestions}, Number of answers posted: {user.noOfAnswers}</li>
                ))}
            </ul>
        </div>
    );
}

export default DetailedUsers;