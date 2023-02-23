import React from "react";
import ReactDOM from "react-dom/client";
import { Link } from "react-router-dom";

import { useState, useEffect } from "react";

const fetchQuestions = async (signal) => {
  return await (await fetch("/questions/all", { signal })).json();
};

// const deleteDivison = async(id) => {
//   return await (await fetch(`/api/divisons/${id}`, {
//     method: "DELETE",
//   })).json()
// }

const Home = () => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  //   const handleDelete = async (id) => {
  //     try {
  //       const res = await deleteDivison(id)
  //       console.log(res)
  //     } catch (err) {
  //       console.log(err)
  //     }
  //   }

  useEffect(() => {
    const controller = new AbortController();
    const getAllQuestions = async () => {
      try {
        const data = await fetchQuestions(controller.signal);
        console.log(data);
        setData(data);
        setLoading(false);
      } catch (err) {
        console.log(err);
      }
    };
    getAllQuestions();
    return () => controller.abort();
  }, []);

  if (loading) return <div>Loading</div>;

  return (
    <div>
      <div>
        <div>
          Index
        </div>
        <div>
          Title
        </div>
        <div>
          Number of answers
        </div>
        <div>
          Username
        </div>
      </div>
      <div className="questions">
        {data.map((question, index) => (
          <div key={question.questionId}>
            <div>{index + 1}</div>
            <div>{question.title}</div>
            <div>{question.numberOfAnswers}</div>
            <div>{question.username}</div>
            <Link to={`/questions/${question.questionId}`}>
              <button variant="outlined">View</button>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
