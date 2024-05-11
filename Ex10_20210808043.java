import java.util.*;

public class Ex10_20210808043 {

}

class User {
	private int id;
	private String username, email;
	private Set<User> followers, following;
	private Set<Post> likedPosts;
	private Map<User, Queue<Message>> messages;

	public User(String username, String email) {
		this.username = username;
		this.email = email;
		this.followers = new HashSet<>();
		this.following = new HashSet<>();
		this.likedPosts = new HashSet<>();
		this.messages = new HashMap<>();
		this.id = hashCode();
	}

	public String getUsername() {
		return this.username;
	}

	public String getEmail() {
		return this.email;
	}

	public Set<User> getFollowers() {
		return this.followers;
	}

	public Set<User> getFollowing() {
		return this.following;
	}

	public Set<Post> getLikedPosts() {
		return this.likedPosts;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void message(User recipient, String content) {
		if (!messages.containsKey(recipient)) {
			messages.put(recipient, new LinkedList<>());
			recipient.messages.put(this, new LinkedList<>());
		}

		Message newMessage = new Message(this, content);
		messages.get(recipient).add(newMessage);
		recipient.messages.get(this).add(newMessage);

		read(recipient);
	}

	public void read(User user) {
		for (User messagesKey : messages.keySet()) {
			System.out.println(messages.get(messagesKey));
		}
	}

	public void follow(User user) {
		if (!getFollowing().contains(user)) {
			getFollowing().add(user);
			user.getFollowing().add(user);
		} else {
			getFollowing().remove(user);
			user.getFollowers().remove(this);
		}
	}

	public void like(Post post) {
		if (!likedPosts.contains(post))likedPosts.add(post);
		else likedPosts.remove(post);
		post.likedBy(this);
	}

	public Post post(String content) {
		return new Post(content);
	}

	public Comment comment(Post post, String content) {
		Comment newComm = new Comment(content);
		post.commentBy(this, newComm);
		return newComm;
	}

	@Override
	public boolean equals(Object obj) {
		final User otherId = (User) obj;
		return this.id == otherId.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
}

class Message {
	private boolean seen;
	private java.util.Date dateSent;
	private String content;
	private User sender;

	public Message(User sender, String content) {
		this.sender = sender;
		this.content = content;
		this.seen = false;
		this.dateSent = new java.util.Date();
	}

	public String read(User reader) {
		if (!sender.equals(reader)) seen = true;
		System.out.println("Sent at: " + this.dateSent);
		return this.content;
	}

	public boolean hasRead() {
		return this.seen;
	}
}

class Post {
	private java.util.Date datePosted;
	private String content;
	private Set<User> likes;
	private Map<User, ArrayList<Comment>> comments;

	public Post(String content) {
		this.datePosted = new java.util.Date();
		this.content = content;
		this.likes = new HashSet<>();
		this.comments = new HashMap<>();
	}

	public boolean likedBy(User user) {
		if (!likes.contains(user)) {
			likes.add(user);
			return true;
		} else {
			likes.remove(user);
			return false;
		}
	}

	public boolean commentBy(User user, Comment comment) {
		if (!comments.containsKey(user)) {
			comments.put(user, new ArrayList<>());
			comments.get(user).add(comment);return true;
		} else {
			if (comments.get(user).indexOf(comment) != -1) return false;
			comments.get(user).add(comment);return true;
		}
	}

	public String getContent() {
		System.out.println("Posted at: " + datePosted);return content;
	}

	public Comment getComment(User user, int index) {
		if (comments.containsKey(user)) if (index < comments.get(user).size() && index >= 0) return comments.get(user).get(index);
		return null;
	}

	public int getCommentCount() {
		int commCount = 0;
		for (ArrayList<Comment> comments : comments.values()) commCount += comments.size();
		return commCount;
	}

	public int getCommentCountByUser(User user) {
		if (comments.containsKey(user)) return comments.get(user).size();
		return 0;
	}
}

class Comment extends Post {
	public Comment(String content) {
		super(content);
	}
}

class SocialNetwork {
	private static Map<User, ArrayList<Post>> postByUsers;

	public SocialNetwork() {
		postByUsers = new HashMap<>();
	}

	public static User register(String username, String email) {
		User userNew = new User(username, email);
		if (!postByUsers.containsKey(userNew)) {
			postByUsers.put(userNew, new ArrayList<>());return userNew;
		}
		return null;
	}

	public static Post post(User user, String content) {
		if (register(user.getUsername(), user.getEmail()) != null) {
			Post postNew = new Post(content);
			postByUsers.get(user).add(postNew);
			return postNew;
		}
		return null;
	}

	public static User getUser(String email) {
		int id = Objects.hash(email);
		for (User user : postByUsers.keySet()) {
			if (user.hashCode() == id) {
				return user;
			}
		}
		return null;
	}

	public static Set<Post> getFeed(User user) {
		HashSet<Post> posts = new HashSet<>();
		for (User users : user.getFollowing()) {
			posts.addAll(postByUsers.get(users));
		}
		return posts;
	}

	public static Map<User, String> search(String keyword) {
		Map<User, String> searchResults = new HashMap<>();
		for (User user : postByUsers.keySet()) {
			if (user.getUsername().toLowerCase().contains(keyword.toLowerCase())) searchResults.put(user, user.getUsername());
		}
		return searchResults;
	}

	public static Map<Post, Set<User>> reverseMap(Map<User, ArrayList<Post>> postByUsers) {
		Map<Post, Set<User>> oppositeMap = new HashMap<>();
		for (Map.Entry<User, ArrayList<Post>> entry : postByUsers.entrySet()) {
			User user = entry.getKey();
			ArrayList<Post> posts = entry.getValue();
			for (Post post : posts) {
				if (!oppositeMap.containsKey(post)) {
					oppositeMap.put(post, new HashSet<>());
				}
				Set<User> users = oppositeMap.get(post);
				users.add(user);
				oppositeMap.put(post, users);
			}
		}
		return oppositeMap;
	}
}
