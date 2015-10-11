package com.agmoo.picture;

import java.util.ArrayList;

public class QuestInfo
{
	private int respCode;
	private String respDesc;
	private ArrayList<Question> results = new ArrayList<Question>();
	private int allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize;

	public int getRespCode()
	{
		return respCode;
	}

	public void setRespCode(int respCode)
	{
		this.respCode = respCode;
	}

	public String getRespDesc()
	{
		return respDesc;
	}

	public void setRespDesc(String respDesc)
	{
		this.respDesc = respDesc;
	}

	public ArrayList<Question> getResults()
	{
		return results;
	}

	public void setResults(ArrayList<Question> results)
	{
		this.results = results;
	}

	public int getAllRow()
	{
		return allRow;
	}

	public void setAllRow(int allRow)
	{
		this.allRow = allRow;
	}

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	@Override
	public String toString()
	{
		return "QuestInfo [respCode=" + respCode + ", respDesc=" + respDesc + ", results=" + results + ", allRow=" + allRow + ", totalPage="
				+ totalPage + ", currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}

	public class Question
	{
		private int questionID;
		private String userID;
		private String title;
		private String description;// 问题描述
		private String updateDate; // 咨询时间
		private String timingDescription; // 咨询时间
		private ArrayList<String> attachmentsPath; // 图片
		private boolean resolved; // 是否采纳
		private int answerCount; // 回答总数
		private ArrayList<QuestAnswer> answers = new ArrayList<QuestInfo.QuestAnswer>();

		public int getQuestionID()
		{
			return questionID;
		}

		public void setQuestionID(int questionID)
		{
			this.questionID = questionID;
		}

		public String getUserID()
		{
			return userID;
		}

		public void setUserID(String userID)
		{
			this.userID = userID;
		}

		public String getTitle()
		{
			return title;
		}

		public void setTitle(String title)
		{
			this.title = title;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public String getUpdateDate()
		{
			return updateDate;
		}

		public void setUpdateDate(String updateDate)
		{
			this.updateDate = updateDate;
		}

		public String getTimingDescription()
		{
			return timingDescription;
		}

		public void setTimingDescription(String timingDescription)
		{
			this.timingDescription = timingDescription;
		}

		public ArrayList<String> getAttachmentsPath()
		{
			return attachmentsPath;
		}

		public void setAttachmentsPath(ArrayList<String> attachmentsPath)
		{
			this.attachmentsPath = attachmentsPath;
		}

		public boolean isResolved()
		{
			return resolved;
		}

		public void setResolved(boolean resolved)
		{
			this.resolved = resolved;
		}

		public int getAnswerCount()
		{
			return answerCount;
		}

		public void setAnswerCount(int answerCount)
		{
			this.answerCount = answerCount;
		}

		public ArrayList<QuestAnswer> getAnswers()
		{
			return answers;
		}

		public void setAnswers(ArrayList<QuestAnswer> answers)
		{
			this.answers = answers;
		}

		@Override
		public String toString()
		{
			return "Question [questionID=" + questionID + ", userID=" + userID + ", title=" + title + ", description=" + description
					+ ", updateDate=" + updateDate + ", timingDescription=" + timingDescription + ", attachmentsPath=" + attachmentsPath
					+ ", resolved=" + resolved + ", answerCount=" + answerCount + ", answers=" + answers + "]";
		}

	}

	public class QuestAnswer
	{
		private int answerID;
		private int questionID;
		private EngineerInfo user = new EngineerInfo();
		private String content;
		private String createTime;
		private String status;
		private String accept;
		private String timingDescription;
		private ArrayList<String> appendQuestion;
		public int getAnswerID()
		{
			return answerID;
		}
		public void setAnswerID(int answerID)
		{
			this.answerID = answerID;
		}
		public int getQuestionID()
		{
			return questionID;
		}
		public void setQuestionID(int questionID)
		{
			this.questionID = questionID;
		}
		public EngineerInfo getUser()
		{
			return user;
		}
		public void setUser(EngineerInfo user)
		{
			this.user = user;
		}
		public String getContent()
		{
			return content;
		}
		public void setContent(String content)
		{
			this.content = content;
		}
		public String getCreateTime()
		{
			return createTime;
		}
		public void setCreateTime(String createTime)
		{
			this.createTime = createTime;
		}
		public String getStatus()
		{
			return status;
		}
		public void setStatus(String status)
		{
			this.status = status;
		}
		public String getAccept()
		{
			return accept;
		}
		public void setAccept(String accept)
		{
			this.accept = accept;
		}
		public String getTimingDescription()
		{
			return timingDescription;
		}
		public void setTimingDescription(String timingDescription)
		{
			this.timingDescription = timingDescription;
		}
		public ArrayList<String> getAppendQuestion()
		{
			return appendQuestion;
		}
		public void setAppendQuestion(ArrayList<String> appendQuestion)
		{
			this.appendQuestion = appendQuestion;
		}
		@Override
		public String toString()
		{
			return "QuestAnswer [answerID=" + answerID + ", questionID=" + questionID + ", user=" + user + ", content=" + content + ", createTime="
					+ createTime + ", status=" + status + ", accept=" + accept + ", timingDescription=" + timingDescription + ", appendQuestion="
					+ appendQuestion + "]";
		}

		 

	}
}
