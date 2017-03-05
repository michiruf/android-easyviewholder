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

## How to use ListView Adapter
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

## Jow to use RecyclerView Adapter
Initialize the adapter
```java
RecyclerHolderArrayAdapter<Item> adapter = new RecyclerHolderArrayAdapter<Item>(R.layout.your_item_layout) {
    @Override
    protected ViewHolder<Item> constructHolder(View view) {
        return new ItemHolder(view);
    }
};
recyclerView.setAdapter(adapter);
```

Build your holder extending RecyclerHolderArrayAdapter.ViewHolder class
```java
class ItemHolder extends RecyclerHolderArrayAdapter.ViewHolder<Item> {

    protected TextView textView;

    ItemHolder(View view) {
        super(view);
        // Find the views and bind them to the fields
    }

    @Override
    public void apply(Item item, int position) {
        // Apply your changes. For example:
        textView.setText(item.getName());
    }
}
```

UPDATE:
Since the RecyclerView does not support OnItemClickListeners v0.5 will support this.

## License

    Copyright 2015 Michael Ruf

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
