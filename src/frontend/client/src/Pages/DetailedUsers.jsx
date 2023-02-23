import React from 'react';
import useState from 'react';
import useEffect from 'react';

const DetailedUsers = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch("user/all")
            .then((response) => response.json())
            .then((data) => setUsers(data));
    }, []);

    return (
        <div>
            <h2>User List</h2>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    );
}

export default DetailedUsers;