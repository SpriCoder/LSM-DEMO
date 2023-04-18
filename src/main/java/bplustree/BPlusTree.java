/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package bplustree;

import java.util.Iterator;

public class BPlusTree<K extends Comparable<K>, V> implements Iterable<BPlusRecord<K, V>> {

  // the root of tree
  protected BPlusNode<K, V> root;

  // the order of tree
  protected int order;

  // the head of leaf node
  protected BPlusNode<K, V> head;

  // the height of tree
  protected int height = 0;

  public BPlusNode<K, V> getHead() {
    return head;
  }

  public void setHead(BPlusNode<K, V> head) {
    this.head = head;
  }

  public BPlusNode<K, V> getRoot() {
    return root;
  }

  public void setRoot(BPlusNode<K, V> root) {
    this.root = root;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    return height;
  }

  public BPlusRecord<K, V> get(K key) {
    return root.get(key);
  }

  public V remove(K key) {
    return root.remove(key, this);
  }

  public void insertOrUpdate(K key, V value) {
    root.insertOrUpdate(new BPlusRecord<>(key, value), this);
  }

  public void insertRemoveFlag(K key) {
    root.insertOrUpdate(new BPlusRecord<>(key, true), this);
  }

  public BPlusTree(int order) {
    if (order < 3) {
      System.out.print("order must be greater than 2");
      System.exit(0);
    }
    this.order = order;
    root = new BPlusNode<K, V>(true, true);
    head = root;
  }

  public void print() {
    this.root.print(0);
  }

  @Override
  public Iterator<BPlusRecord<K, V>> iterator() {
    return new BPlusTreeIterator<>(this);
  }
}
