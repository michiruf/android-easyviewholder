# Easy view holder for android
A simple library to use view holders with less boilerplate.

## Embed via Gradle
Add the repository
```gradle
repositories {
    // ...
    maven { url 'https://jitpack.io' }
    // ...
}
```

Add the dependency
```gradle
dependencies {
    // ...
    compile 'com.github.michiruf:android-easyviewholder:0.2'
    // ...
}
```

## How to use
Initialize the adapter
```java
HolderArrayAdapter<Item> adapter = new HolderArrayAdapter<Item>(getActivity(), R.layout.your_item_layout) {
    @Override
    protected ViewHolder<Item> constructHolder(View view) {
        return new ItemHolder(view);
    }
};
listView.setAdapter(adapter);
```

Build your holder implementing the ViewHolder interface
```java
protected class ItemHolder implements HolderArrayAdapter.ViewHolder<Item> {

    protected TextView textView;

    public ItemHolder(View view) {
        // Find the views and bind them to the fields
    }

    @Override
    public void apply(Item item, int position) {
        // Apply your changes. For example:
        textView.setText(item.getName());
    }
}
```
