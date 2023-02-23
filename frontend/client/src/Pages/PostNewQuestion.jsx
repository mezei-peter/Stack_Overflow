import React, { useState } from 'react';

const PostNewQuestion = () => {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [userId, setUserId] = useState(-1);
    const [sessionId, setSessionId] = useState("");

    const fetchUserId= async (sessionId) => {
        return await fetch("/user/userIdFromSessionId/" + sessionId)
            .then(res => res.json())
            .then(msg => {
                return msg;
            });
    }

    const handleSubmit = async e => {
        e.preventDefault();
        const userId = await fetchUserId(localStorage.getItem("sessionId"));
    }

    return(
        <div>
            <form>
                <textarea type="text" required placeholder='Question title' onChange={e => setTitle(e.target.value)}/>
                <textarea type="text" required placeholder='Description' onChange={e => setTitle(e.target.value)}/>
                <button onClick={(e) => handleSubmit(e)}>Submit</button>
            </form>
        </div>
    )
}

export default PostNewQuestion;