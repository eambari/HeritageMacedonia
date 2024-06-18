// About.js
import React from "react";
import "./About.css";

const About = () => {
    const teamMembers = [
        { id: 1, name: 'Endrit Ambari', role: 'Software Developer' },
    ];

    return (
        <div className='main-div'>
            <div>
                <h1 className="header">About</h1>
            </div>
            <div className="text-header">
                <div className="text-content">
                    <h3>Who Am I</h3>
                    <p>
                        I am a student currently studying Computer Science at the
                        'Faculty of Computer Science and Engineering - Skopje'. My job is to make
                        a project or an application for users who want to explore heritage sites in Macedonia.
                    </p>
                    <h3>The Purpose of the System</h3>
                    <p>
                        The system allows users to view the map along with heritage sites.
                        The map displays all the heritage sites in North Macedonia.
                        Heritage sites are displayed in detail
                        on a new page when clicked, and they can be found by searching in the search bar or using filters.
                    </p>
                </div>
            </div>
            <div className="team-section">
                <h3>
                    {teamMembers.map((member) => (
                        <p key={member.id}>{`${member.name}`}</p>
                    ))}
                </h3>
            </div>
        </div>
    );
};

export default About;
